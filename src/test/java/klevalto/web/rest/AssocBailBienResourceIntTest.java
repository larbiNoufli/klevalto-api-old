package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.AssocBailBien;
import klevalto.repository.AssocBailBienRepository;
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
 * Test class for the AssocBailBienResource REST controller.
 *
 * @see AssocBailBienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class AssocBailBienResourceIntTest {

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
    private AssocBailBienRepository assocBailBienRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssocBailBienMockMvc;

    private AssocBailBien assocBailBien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssocBailBienResource assocBailBienResource = new AssocBailBienResource(assocBailBienRepository);
        this.restAssocBailBienMockMvc = MockMvcBuilders.standaloneSetup(assocBailBienResource)
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
    public static AssocBailBien createEntity(EntityManager em) {
        AssocBailBien assocBailBien = new AssocBailBien()
            .isActif(DEFAULT_IS_ACTIF)
            .datedebutjuridique(DEFAULT_DATEDEBUTJURIDIQUE)
            .datefinjuridique(DEFAULT_DATEFINJURIDIQUE)
            .isValidated(DEFAULT_IS_VALIDATED)
            .validationDate(DEFAULT_VALIDATION_DATE)
            .isRejected(DEFAULT_IS_REJECTED)
            .refusalDate(DEFAULT_REFUSAL_DATE)
            .valeurAchatDuBien(DEFAULT_VALEUR_ACHAT_DU_BIEN);
        return assocBailBien;
    }

    @Before
    public void initTest() {
        assocBailBien = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssocBailBien() throws Exception {
        int databaseSizeBeforeCreate = assocBailBienRepository.findAll().size();

        // Create the AssocBailBien
        restAssocBailBienMockMvc.perform(post("/api/assoc-bail-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assocBailBien)))
            .andExpect(status().isCreated());

        // Validate the AssocBailBien in the database
        List<AssocBailBien> assocBailBienList = assocBailBienRepository.findAll();
        assertThat(assocBailBienList).hasSize(databaseSizeBeforeCreate + 1);
        AssocBailBien testAssocBailBien = assocBailBienList.get(assocBailBienList.size() - 1);
        assertThat(testAssocBailBien.isIsActif()).isEqualTo(DEFAULT_IS_ACTIF);
        assertThat(testAssocBailBien.getDatedebutjuridique()).isEqualTo(DEFAULT_DATEDEBUTJURIDIQUE);
        assertThat(testAssocBailBien.getDatefinjuridique()).isEqualTo(DEFAULT_DATEFINJURIDIQUE);
        assertThat(testAssocBailBien.isIsValidated()).isEqualTo(DEFAULT_IS_VALIDATED);
        assertThat(testAssocBailBien.getValidationDate()).isEqualTo(DEFAULT_VALIDATION_DATE);
        assertThat(testAssocBailBien.isIsRejected()).isEqualTo(DEFAULT_IS_REJECTED);
        assertThat(testAssocBailBien.getRefusalDate()).isEqualTo(DEFAULT_REFUSAL_DATE);
        assertThat(testAssocBailBien.getValeurAchatDuBien()).isEqualTo(DEFAULT_VALEUR_ACHAT_DU_BIEN);
    }

    @Test
    @Transactional
    public void createAssocBailBienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assocBailBienRepository.findAll().size();

        // Create the AssocBailBien with an existing ID
        assocBailBien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssocBailBienMockMvc.perform(post("/api/assoc-bail-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assocBailBien)))
            .andExpect(status().isBadRequest());

        // Validate the AssocBailBien in the database
        List<AssocBailBien> assocBailBienList = assocBailBienRepository.findAll();
        assertThat(assocBailBienList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssocBailBiens() throws Exception {
        // Initialize the database
        assocBailBienRepository.saveAndFlush(assocBailBien);

        // Get all the assocBailBienList
        restAssocBailBienMockMvc.perform(get("/api/assoc-bail-biens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assocBailBien.getId().intValue())))
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
    public void getAssocBailBien() throws Exception {
        // Initialize the database
        assocBailBienRepository.saveAndFlush(assocBailBien);

        // Get the assocBailBien
        restAssocBailBienMockMvc.perform(get("/api/assoc-bail-biens/{id}", assocBailBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assocBailBien.getId().intValue()))
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
    public void getNonExistingAssocBailBien() throws Exception {
        // Get the assocBailBien
        restAssocBailBienMockMvc.perform(get("/api/assoc-bail-biens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssocBailBien() throws Exception {
        // Initialize the database
        assocBailBienRepository.saveAndFlush(assocBailBien);
        int databaseSizeBeforeUpdate = assocBailBienRepository.findAll().size();

        // Update the assocBailBien
        AssocBailBien updatedAssocBailBien = assocBailBienRepository.findOne(assocBailBien.getId());
        updatedAssocBailBien
            .isActif(UPDATED_IS_ACTIF)
            .datedebutjuridique(UPDATED_DATEDEBUTJURIDIQUE)
            .datefinjuridique(UPDATED_DATEFINJURIDIQUE)
            .isValidated(UPDATED_IS_VALIDATED)
            .validationDate(UPDATED_VALIDATION_DATE)
            .isRejected(UPDATED_IS_REJECTED)
            .refusalDate(UPDATED_REFUSAL_DATE)
            .valeurAchatDuBien(UPDATED_VALEUR_ACHAT_DU_BIEN);

        restAssocBailBienMockMvc.perform(put("/api/assoc-bail-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssocBailBien)))
            .andExpect(status().isOk());

        // Validate the AssocBailBien in the database
        List<AssocBailBien> assocBailBienList = assocBailBienRepository.findAll();
        assertThat(assocBailBienList).hasSize(databaseSizeBeforeUpdate);
        AssocBailBien testAssocBailBien = assocBailBienList.get(assocBailBienList.size() - 1);
        assertThat(testAssocBailBien.isIsActif()).isEqualTo(UPDATED_IS_ACTIF);
        assertThat(testAssocBailBien.getDatedebutjuridique()).isEqualTo(UPDATED_DATEDEBUTJURIDIQUE);
        assertThat(testAssocBailBien.getDatefinjuridique()).isEqualTo(UPDATED_DATEFINJURIDIQUE);
        assertThat(testAssocBailBien.isIsValidated()).isEqualTo(UPDATED_IS_VALIDATED);
        assertThat(testAssocBailBien.getValidationDate()).isEqualTo(UPDATED_VALIDATION_DATE);
        assertThat(testAssocBailBien.isIsRejected()).isEqualTo(UPDATED_IS_REJECTED);
        assertThat(testAssocBailBien.getRefusalDate()).isEqualTo(UPDATED_REFUSAL_DATE);
        assertThat(testAssocBailBien.getValeurAchatDuBien()).isEqualTo(UPDATED_VALEUR_ACHAT_DU_BIEN);
    }

    @Test
    @Transactional
    public void updateNonExistingAssocBailBien() throws Exception {
        int databaseSizeBeforeUpdate = assocBailBienRepository.findAll().size();

        // Create the AssocBailBien

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssocBailBienMockMvc.perform(put("/api/assoc-bail-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assocBailBien)))
            .andExpect(status().isCreated());

        // Validate the AssocBailBien in the database
        List<AssocBailBien> assocBailBienList = assocBailBienRepository.findAll();
        assertThat(assocBailBienList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAssocBailBien() throws Exception {
        // Initialize the database
        assocBailBienRepository.saveAndFlush(assocBailBien);
        int databaseSizeBeforeDelete = assocBailBienRepository.findAll().size();

        // Get the assocBailBien
        restAssocBailBienMockMvc.perform(delete("/api/assoc-bail-biens/{id}", assocBailBien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AssocBailBien> assocBailBienList = assocBailBienRepository.findAll();
        assertThat(assocBailBienList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssocBailBien.class);
        AssocBailBien assocBailBien1 = new AssocBailBien();
        assocBailBien1.setId(1L);
        AssocBailBien assocBailBien2 = new AssocBailBien();
        assocBailBien2.setId(assocBailBien1.getId());
        assertThat(assocBailBien1).isEqualTo(assocBailBien2);
        assocBailBien2.setId(2L);
        assertThat(assocBailBien1).isNotEqualTo(assocBailBien2);
        assocBailBien1.setId(null);
        assertThat(assocBailBien1).isNotEqualTo(assocBailBien2);
    }
}
