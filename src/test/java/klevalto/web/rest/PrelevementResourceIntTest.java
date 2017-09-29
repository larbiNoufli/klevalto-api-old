package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.Prelevement;
import klevalto.repository.PrelevementRepository;
import klevalto.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static klevalto.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PrelevementResource REST controller.
 *
 * @see PrelevementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class PrelevementResourceIntTest {

    private static final Double DEFAULT_MONTANT_A_PRELEVER = 1D;
    private static final Double UPDATED_MONTANT_A_PRELEVER = 2D;

    private static final Integer DEFAULT_NUMERO_ORDRE = 1;
    private static final Integer UPDATED_NUMERO_ORDRE = 2;

    private static final ZonedDateTime DEFAULT_DATE_DU_PRELEVEMENT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DU_PRELEVEMENT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_RECURRENT = false;
    private static final Boolean UPDATED_IS_RECURRENT = true;

    private static final ZonedDateTime DEFAULT_DATE_DETRANSFERT_VERS_MAYA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DETRANSFERT_VERS_MAYA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private PrelevementRepository prelevementRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPrelevementMockMvc;

    private Prelevement prelevement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PrelevementResource prelevementResource = new PrelevementResource(prelevementRepository);
        this.restPrelevementMockMvc = MockMvcBuilders.standaloneSetup(prelevementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prelevement createEntity(EntityManager em) {
        Prelevement prelevement = new Prelevement()
            .montantAPrelever(DEFAULT_MONTANT_A_PRELEVER)
            .numeroOrdre(DEFAULT_NUMERO_ORDRE)
            .dateDuPrelevement(DEFAULT_DATE_DU_PRELEVEMENT)
            .isRecurrent(DEFAULT_IS_RECURRENT)
            .dateDetransfertVersMaya(DEFAULT_DATE_DETRANSFERT_VERS_MAYA);
        return prelevement;
    }

    @Before
    public void initTest() {
        prelevement = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrelevement() throws Exception {
        int databaseSizeBeforeCreate = prelevementRepository.findAll().size();

        // Create the Prelevement
        restPrelevementMockMvc.perform(post("/api/prelevements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prelevement)))
            .andExpect(status().isCreated());

        // Validate the Prelevement in the database
        List<Prelevement> prelevementList = prelevementRepository.findAll();
        assertThat(prelevementList).hasSize(databaseSizeBeforeCreate + 1);
        Prelevement testPrelevement = prelevementList.get(prelevementList.size() - 1);
        assertThat(testPrelevement.getMontantAPrelever()).isEqualTo(DEFAULT_MONTANT_A_PRELEVER);
        assertThat(testPrelevement.getNumeroOrdre()).isEqualTo(DEFAULT_NUMERO_ORDRE);
        assertThat(testPrelevement.getDateDuPrelevement()).isEqualTo(DEFAULT_DATE_DU_PRELEVEMENT);
        assertThat(testPrelevement.isIsRecurrent()).isEqualTo(DEFAULT_IS_RECURRENT);
        assertThat(testPrelevement.getDateDetransfertVersMaya()).isEqualTo(DEFAULT_DATE_DETRANSFERT_VERS_MAYA);
    }

    @Test
    @Transactional
    public void createPrelevementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prelevementRepository.findAll().size();

        // Create the Prelevement with an existing ID
        prelevement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrelevementMockMvc.perform(post("/api/prelevements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prelevement)))
            .andExpect(status().isBadRequest());

        // Validate the Prelevement in the database
        List<Prelevement> prelevementList = prelevementRepository.findAll();
        assertThat(prelevementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPrelevements() throws Exception {
        // Initialize the database
        prelevementRepository.saveAndFlush(prelevement);

        // Get all the prelevementList
        restPrelevementMockMvc.perform(get("/api/prelevements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prelevement.getId().intValue())))
            .andExpect(jsonPath("$.[*].montantAPrelever").value(hasItem(DEFAULT_MONTANT_A_PRELEVER.doubleValue())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)))
            .andExpect(jsonPath("$.[*].dateDuPrelevement").value(hasItem(sameInstant(DEFAULT_DATE_DU_PRELEVEMENT))))
            .andExpect(jsonPath("$.[*].isRecurrent").value(hasItem(DEFAULT_IS_RECURRENT.booleanValue())))
            .andExpect(jsonPath("$.[*].dateDetransfertVersMaya").value(hasItem(sameInstant(DEFAULT_DATE_DETRANSFERT_VERS_MAYA))));
    }

    @Test
    @Transactional
    public void getPrelevement() throws Exception {
        // Initialize the database
        prelevementRepository.saveAndFlush(prelevement);

        // Get the prelevement
        restPrelevementMockMvc.perform(get("/api/prelevements/{id}", prelevement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(prelevement.getId().intValue()))
            .andExpect(jsonPath("$.montantAPrelever").value(DEFAULT_MONTANT_A_PRELEVER.doubleValue()))
            .andExpect(jsonPath("$.numeroOrdre").value(DEFAULT_NUMERO_ORDRE))
            .andExpect(jsonPath("$.dateDuPrelevement").value(sameInstant(DEFAULT_DATE_DU_PRELEVEMENT)))
            .andExpect(jsonPath("$.isRecurrent").value(DEFAULT_IS_RECURRENT.booleanValue()))
            .andExpect(jsonPath("$.dateDetransfertVersMaya").value(sameInstant(DEFAULT_DATE_DETRANSFERT_VERS_MAYA)));
    }

    @Test
    @Transactional
    public void getNonExistingPrelevement() throws Exception {
        // Get the prelevement
        restPrelevementMockMvc.perform(get("/api/prelevements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrelevement() throws Exception {
        // Initialize the database
        prelevementRepository.saveAndFlush(prelevement);
        int databaseSizeBeforeUpdate = prelevementRepository.findAll().size();

        // Update the prelevement
        Prelevement updatedPrelevement = prelevementRepository.findOne(prelevement.getId());
        updatedPrelevement
            .montantAPrelever(UPDATED_MONTANT_A_PRELEVER)
            .numeroOrdre(UPDATED_NUMERO_ORDRE)
            .dateDuPrelevement(UPDATED_DATE_DU_PRELEVEMENT)
            .isRecurrent(UPDATED_IS_RECURRENT)
            .dateDetransfertVersMaya(UPDATED_DATE_DETRANSFERT_VERS_MAYA);

        restPrelevementMockMvc.perform(put("/api/prelevements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPrelevement)))
            .andExpect(status().isOk());

        // Validate the Prelevement in the database
        List<Prelevement> prelevementList = prelevementRepository.findAll();
        assertThat(prelevementList).hasSize(databaseSizeBeforeUpdate);
        Prelevement testPrelevement = prelevementList.get(prelevementList.size() - 1);
        assertThat(testPrelevement.getMontantAPrelever()).isEqualTo(UPDATED_MONTANT_A_PRELEVER);
        assertThat(testPrelevement.getNumeroOrdre()).isEqualTo(UPDATED_NUMERO_ORDRE);
        assertThat(testPrelevement.getDateDuPrelevement()).isEqualTo(UPDATED_DATE_DU_PRELEVEMENT);
        assertThat(testPrelevement.isIsRecurrent()).isEqualTo(UPDATED_IS_RECURRENT);
        assertThat(testPrelevement.getDateDetransfertVersMaya()).isEqualTo(UPDATED_DATE_DETRANSFERT_VERS_MAYA);
    }

    @Test
    @Transactional
    public void updateNonExistingPrelevement() throws Exception {
        int databaseSizeBeforeUpdate = prelevementRepository.findAll().size();

        // Create the Prelevement

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPrelevementMockMvc.perform(put("/api/prelevements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prelevement)))
            .andExpect(status().isCreated());

        // Validate the Prelevement in the database
        List<Prelevement> prelevementList = prelevementRepository.findAll();
        assertThat(prelevementList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePrelevement() throws Exception {
        // Initialize the database
        prelevementRepository.saveAndFlush(prelevement);
        int databaseSizeBeforeDelete = prelevementRepository.findAll().size();

        // Get the prelevement
        restPrelevementMockMvc.perform(delete("/api/prelevements/{id}", prelevement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Prelevement> prelevementList = prelevementRepository.findAll();
        assertThat(prelevementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Prelevement.class);
        Prelevement prelevement1 = new Prelevement();
        prelevement1.setId(1L);
        Prelevement prelevement2 = new Prelevement();
        prelevement2.setId(prelevement1.getId());
        assertThat(prelevement1).isEqualTo(prelevement2);
        prelevement2.setId(2L);
        assertThat(prelevement1).isNotEqualTo(prelevement2);
        prelevement1.setId(null);
        assertThat(prelevement1).isNotEqualTo(prelevement2);
    }
}
