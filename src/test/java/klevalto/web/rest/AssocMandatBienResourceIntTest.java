package klevalto.web.rest;

import static klevalto.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;

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

import klevalto.SergicApp;
import klevalto.domain.AssocMandatBien;
import klevalto.repository.AssocMandatBienRepository;
import klevalto.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the AssocMandatBienResource REST controller.
 *
 * @see AssocMandatBienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class AssocMandatBienResourceIntTest {

    private static final Boolean DEFAULT_IS_ACTIF = false;
    private static final Boolean UPDATED_IS_ACTIF = true;

    private static final ZonedDateTime DEFAULT_DATEDEBUTJURIDIQUE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEDEBUTJURIDIQUE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATEFINJURIDIQUE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEFINJURIDIQUE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_VALIDATED = false;
    private static final Boolean UPDATED_IS_VALIDATED = true;

    private static final ZonedDateTime DEFAULT_VALIDATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_VALIDATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_REJECTED = false;
    private static final Boolean UPDATED_IS_REJECTED = true;

    private static final ZonedDateTime DEFAULT_REFUSAL_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REFUSAL_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_VALEUR_ACHAT_DU_BIEN = 1D;
    private static final Double UPDATED_VALEUR_ACHAT_DU_BIEN = 2D;

    @Autowired
    private AssocMandatBienRepository assocMandatBienRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssocMandatBienMockMvc;

    private AssocMandatBien assocMandatBien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssocMandatBienResource assocMandatBienResource = new AssocMandatBienResource(assocMandatBienRepository);
        this.restAssocMandatBienMockMvc = MockMvcBuilders.standaloneSetup(assocMandatBienResource)
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
    public static AssocMandatBien createEntity(EntityManager em) {
        AssocMandatBien assocMandatBien = new AssocMandatBien()
            .isActif(DEFAULT_IS_ACTIF)
            .datedebutjuridique(DEFAULT_DATEDEBUTJURIDIQUE)
            .datefinjuridique(DEFAULT_DATEFINJURIDIQUE)
            .isValidated(DEFAULT_IS_VALIDATED)
            .validationDate(DEFAULT_VALIDATION_DATE)
            .isRejected(DEFAULT_IS_REJECTED)
            .refusalDate(DEFAULT_REFUSAL_DATE)
            .valeurAchatDuBien(DEFAULT_VALEUR_ACHAT_DU_BIEN);
        return assocMandatBien;
    }

    @Before
    public void initTest() {
        assocMandatBien = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssocMandatBien() throws Exception {
        int databaseSizeBeforeCreate = assocMandatBienRepository.findAll().size();

        // Create the AssocMandatBien
        restAssocMandatBienMockMvc.perform(post("/api/assoc-mandat-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assocMandatBien)))
            .andExpect(status().isCreated());

        // Validate the AssocMandatBien in the database
        List<AssocMandatBien> assocMandatBienList = assocMandatBienRepository.findAll();
        assertThat(assocMandatBienList).hasSize(databaseSizeBeforeCreate + 1);
        AssocMandatBien testAssocMandatBien = assocMandatBienList.get(assocMandatBienList.size() - 1);
        assertThat(testAssocMandatBien.isIsActif()).isEqualTo(DEFAULT_IS_ACTIF);
        assertThat(testAssocMandatBien.getDatedebutjuridique()).isEqualTo(DEFAULT_DATEDEBUTJURIDIQUE);
        assertThat(testAssocMandatBien.getDatefinjuridique()).isEqualTo(DEFAULT_DATEFINJURIDIQUE);
        assertThat(testAssocMandatBien.isIsValidated()).isEqualTo(DEFAULT_IS_VALIDATED);
        assertThat(testAssocMandatBien.getValidationDate()).isEqualTo(DEFAULT_VALIDATION_DATE);
        assertThat(testAssocMandatBien.isIsRejected()).isEqualTo(DEFAULT_IS_REJECTED);
        assertThat(testAssocMandatBien.getRefusalDate()).isEqualTo(DEFAULT_REFUSAL_DATE);
        assertThat(testAssocMandatBien.getValeurAchatDuBien()).isEqualTo(DEFAULT_VALEUR_ACHAT_DU_BIEN);
    }

    @Test
    @Transactional
    public void createAssocMandatBienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assocMandatBienRepository.findAll().size();

        // Create the AssocMandatBien with an existing ID
        assocMandatBien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssocMandatBienMockMvc.perform(post("/api/assoc-mandat-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assocMandatBien)))
            .andExpect(status().isBadRequest());

        // Validate the AssocMandatBien in the database
        List<AssocMandatBien> assocMandatBienList = assocMandatBienRepository.findAll();
        assertThat(assocMandatBienList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssocMandatBiens() throws Exception {
        // Initialize the database
        assocMandatBienRepository.saveAndFlush(assocMandatBien);

        // Get all the assocMandatBienList
        restAssocMandatBienMockMvc.perform(get("/api/assoc-mandat-biens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assocMandatBien.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActif").value(hasItem(DEFAULT_IS_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].datedebutjuridique").value(hasItem(sameInstant(DEFAULT_DATEDEBUTJURIDIQUE))))
            .andExpect(jsonPath("$.[*].datefinjuridique").value(hasItem(sameInstant(DEFAULT_DATEFINJURIDIQUE))))
            .andExpect(jsonPath("$.[*].isValidated").value(hasItem(DEFAULT_IS_VALIDATED.booleanValue())))
            .andExpect(jsonPath("$.[*].validationDate").value(hasItem(sameInstant(DEFAULT_VALIDATION_DATE))))
            .andExpect(jsonPath("$.[*].isRejected").value(hasItem(DEFAULT_IS_REJECTED.booleanValue())))
            .andExpect(jsonPath("$.[*].refusalDate").value(hasItem(sameInstant(DEFAULT_REFUSAL_DATE))))
            .andExpect(jsonPath("$.[*].valeurAchatDuBien").value(hasItem(DEFAULT_VALEUR_ACHAT_DU_BIEN.doubleValue())));
    }

    @Test
    @Transactional
    public void getAssocMandatBien() throws Exception {
        // Initialize the database
        assocMandatBienRepository.saveAndFlush(assocMandatBien);

        // Get the assocMandatBien
        restAssocMandatBienMockMvc.perform(get("/api/assoc-mandat-biens/{id}", assocMandatBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assocMandatBien.getId().intValue()))
            .andExpect(jsonPath("$.isActif").value(DEFAULT_IS_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.datedebutjuridique").value(sameInstant(DEFAULT_DATEDEBUTJURIDIQUE)))
            .andExpect(jsonPath("$.datefinjuridique").value(sameInstant(DEFAULT_DATEFINJURIDIQUE)))
            .andExpect(jsonPath("$.isValidated").value(DEFAULT_IS_VALIDATED.booleanValue()))
            .andExpect(jsonPath("$.validationDate").value(sameInstant(DEFAULT_VALIDATION_DATE)))
            .andExpect(jsonPath("$.isRejected").value(DEFAULT_IS_REJECTED.booleanValue()))
            .andExpect(jsonPath("$.refusalDate").value(sameInstant(DEFAULT_REFUSAL_DATE)))
            .andExpect(jsonPath("$.valeurAchatDuBien").value(DEFAULT_VALEUR_ACHAT_DU_BIEN.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAssocMandatBien() throws Exception {
        // Get the assocMandatBien
        restAssocMandatBienMockMvc.perform(get("/api/assoc-mandat-biens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssocMandatBien() throws Exception {
        // Initialize the database
        assocMandatBienRepository.saveAndFlush(assocMandatBien);
        int databaseSizeBeforeUpdate = assocMandatBienRepository.findAll().size();

        // Update the assocMandatBien
        AssocMandatBien updatedAssocMandatBien = assocMandatBienRepository.findOne(assocMandatBien.getId());
        updatedAssocMandatBien
            .isActif(UPDATED_IS_ACTIF)
            .datedebutjuridique(UPDATED_DATEDEBUTJURIDIQUE)
            .datefinjuridique(UPDATED_DATEFINJURIDIQUE)
            .isValidated(UPDATED_IS_VALIDATED)
            .validationDate(UPDATED_VALIDATION_DATE)
            .isRejected(UPDATED_IS_REJECTED)
            .refusalDate(UPDATED_REFUSAL_DATE)
            .valeurAchatDuBien(UPDATED_VALEUR_ACHAT_DU_BIEN);

        restAssocMandatBienMockMvc.perform(put("/api/assoc-mandat-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssocMandatBien)))
            .andExpect(status().isOk());

        // Validate the AssocMandatBien in the database
        List<AssocMandatBien> assocMandatBienList = assocMandatBienRepository.findAll();
        assertThat(assocMandatBienList).hasSize(databaseSizeBeforeUpdate);
        AssocMandatBien testAssocMandatBien = assocMandatBienList.get(assocMandatBienList.size() - 1);
        assertThat(testAssocMandatBien.isIsActif()).isEqualTo(UPDATED_IS_ACTIF);
        assertThat(testAssocMandatBien.getDatedebutjuridique()).isEqualTo(UPDATED_DATEDEBUTJURIDIQUE);
        assertThat(testAssocMandatBien.getDatefinjuridique()).isEqualTo(UPDATED_DATEFINJURIDIQUE);
        assertThat(testAssocMandatBien.isIsValidated()).isEqualTo(UPDATED_IS_VALIDATED);
        assertThat(testAssocMandatBien.getValidationDate()).isEqualTo(UPDATED_VALIDATION_DATE);
        assertThat(testAssocMandatBien.isIsRejected()).isEqualTo(UPDATED_IS_REJECTED);
        assertThat(testAssocMandatBien.getRefusalDate()).isEqualTo(UPDATED_REFUSAL_DATE);
        assertThat(testAssocMandatBien.getValeurAchatDuBien()).isEqualTo(UPDATED_VALEUR_ACHAT_DU_BIEN);
    }

    @Test
    @Transactional
    public void updateNonExistingAssocMandatBien() throws Exception {
        int databaseSizeBeforeUpdate = assocMandatBienRepository.findAll().size();

        // Create the AssocMandatBien

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssocMandatBienMockMvc.perform(put("/api/assoc-mandat-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assocMandatBien)))
            .andExpect(status().isCreated());

        // Validate the AssocMandatBien in the database
        List<AssocMandatBien> assocMandatBienList = assocMandatBienRepository.findAll();
        assertThat(assocMandatBienList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAssocMandatBien() throws Exception {
        // Initialize the database
        assocMandatBienRepository.saveAndFlush(assocMandatBien);
        int databaseSizeBeforeDelete = assocMandatBienRepository.findAll().size();

        // Get the assocMandatBien
        restAssocMandatBienMockMvc.perform(delete("/api/assoc-mandat-biens/{id}", assocMandatBien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AssocMandatBien> assocMandatBienList = assocMandatBienRepository.findAll();
        assertThat(assocMandatBienList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssocMandatBien.class);
        AssocMandatBien assocMandatBien1 = new AssocMandatBien();
        assocMandatBien1.setId(1L);
        AssocMandatBien assocMandatBien2 = new AssocMandatBien();
        assocMandatBien2.setId(assocMandatBien1.getId());
        assertThat(assocMandatBien1).isEqualTo(assocMandatBien2);
        assocMandatBien2.setId(2L);
        assertThat(assocMandatBien1).isNotEqualTo(assocMandatBien2);
        assocMandatBien1.setId(null);
        assertThat(assocMandatBien1).isNotEqualTo(assocMandatBien2);
    }
}
