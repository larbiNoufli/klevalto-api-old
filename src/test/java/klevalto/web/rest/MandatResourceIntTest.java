package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.Mandat;
import klevalto.repository.MandatRepository;
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
 * Test class for the MandatResource REST controller.
 *
 * @see MandatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class MandatResourceIntTest {

    private static final Boolean DEFAULT_IS_ACTIF = false;
    private static final Boolean UPDATED_IS_ACTIF = true;

    private static final Boolean DEFAULT_DATEDEBUTJURIDIQUE = false;
    private static final Boolean UPDATED_DATEDEBUTJURIDIQUE = true;

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

    private static final Float DEFAULT_VALEUR_ACHAT_DU_BIEN = 1F;
    private static final Float UPDATED_VALEUR_ACHAT_DU_BIEN = 2F;

    @Autowired
    private MandatRepository mandatRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMandatMockMvc;

    private Mandat mandat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MandatResource mandatResource = new MandatResource(mandatRepository);
        this.restMandatMockMvc = MockMvcBuilders.standaloneSetup(mandatResource)
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
    public static Mandat createEntity(EntityManager em) {
        Mandat mandat = new Mandat()
            .isActif(DEFAULT_IS_ACTIF)
            .datedebutjuridique(DEFAULT_DATEDEBUTJURIDIQUE)
            .datefinjuridique(DEFAULT_DATEFINJURIDIQUE)
            .isValidated(DEFAULT_IS_VALIDATED)
            .validationDate(DEFAULT_VALIDATION_DATE)
            .isRejected(DEFAULT_IS_REJECTED)
            .refusalDate(DEFAULT_REFUSAL_DATE)
            .valeurAchatDuBien(DEFAULT_VALEUR_ACHAT_DU_BIEN);
        return mandat;
    }

    @Before
    public void initTest() {
        mandat = createEntity(em);
    }

    @Test
    @Transactional
    public void createMandat() throws Exception {
        int databaseSizeBeforeCreate = mandatRepository.findAll().size();

        // Create the Mandat
        restMandatMockMvc.perform(post("/api/mandats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mandat)))
            .andExpect(status().isCreated());

        // Validate the Mandat in the database
        List<Mandat> mandatList = mandatRepository.findAll();
        assertThat(mandatList).hasSize(databaseSizeBeforeCreate + 1);
        Mandat testMandat = mandatList.get(mandatList.size() - 1);
        assertThat(testMandat.isIsActif()).isEqualTo(DEFAULT_IS_ACTIF);
        assertThat(testMandat.isDatedebutjuridique()).isEqualTo(DEFAULT_DATEDEBUTJURIDIQUE);
        assertThat(testMandat.getDatefinjuridique()).isEqualTo(DEFAULT_DATEFINJURIDIQUE);
        assertThat(testMandat.isIsValidated()).isEqualTo(DEFAULT_IS_VALIDATED);
        assertThat(testMandat.getValidationDate()).isEqualTo(DEFAULT_VALIDATION_DATE);
        assertThat(testMandat.isIsRejected()).isEqualTo(DEFAULT_IS_REJECTED);
        assertThat(testMandat.getRefusalDate()).isEqualTo(DEFAULT_REFUSAL_DATE);
        assertThat(testMandat.getValeurAchatDuBien()).isEqualTo(DEFAULT_VALEUR_ACHAT_DU_BIEN);
    }

    @Test
    @Transactional
    public void createMandatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mandatRepository.findAll().size();

        // Create the Mandat with an existing ID
        mandat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMandatMockMvc.perform(post("/api/mandats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mandat)))
            .andExpect(status().isBadRequest());

        // Validate the Mandat in the database
        List<Mandat> mandatList = mandatRepository.findAll();
        assertThat(mandatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMandats() throws Exception {
        // Initialize the database
        mandatRepository.saveAndFlush(mandat);

        // Get all the mandatList
        restMandatMockMvc.perform(get("/api/mandats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mandat.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActif").value(hasItem(DEFAULT_IS_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].datedebutjuridique").value(hasItem(DEFAULT_DATEDEBUTJURIDIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].datefinjuridique").value(hasItem(sameInstant(DEFAULT_DATEFINJURIDIQUE))))
            .andExpect(jsonPath("$.[*].isValidated").value(hasItem(DEFAULT_IS_VALIDATED.booleanValue())))
            .andExpect(jsonPath("$.[*].validationDate").value(hasItem(sameInstant(DEFAULT_VALIDATION_DATE))))
            .andExpect(jsonPath("$.[*].isRejected").value(hasItem(DEFAULT_IS_REJECTED.booleanValue())))
            .andExpect(jsonPath("$.[*].refusalDate").value(hasItem(sameInstant(DEFAULT_REFUSAL_DATE))))
            .andExpect(jsonPath("$.[*].valeurAchatDuBien").value(hasItem(DEFAULT_VALEUR_ACHAT_DU_BIEN.doubleValue())));
    }

    @Test
    @Transactional
    public void getMandat() throws Exception {
        // Initialize the database
        mandatRepository.saveAndFlush(mandat);

        // Get the mandat
        restMandatMockMvc.perform(get("/api/mandats/{id}", mandat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mandat.getId().intValue()))
            .andExpect(jsonPath("$.isActif").value(DEFAULT_IS_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.datedebutjuridique").value(DEFAULT_DATEDEBUTJURIDIQUE.booleanValue()))
            .andExpect(jsonPath("$.datefinjuridique").value(sameInstant(DEFAULT_DATEFINJURIDIQUE)))
            .andExpect(jsonPath("$.isValidated").value(DEFAULT_IS_VALIDATED.booleanValue()))
            .andExpect(jsonPath("$.validationDate").value(sameInstant(DEFAULT_VALIDATION_DATE)))
            .andExpect(jsonPath("$.isRejected").value(DEFAULT_IS_REJECTED.booleanValue()))
            .andExpect(jsonPath("$.refusalDate").value(sameInstant(DEFAULT_REFUSAL_DATE)))
            .andExpect(jsonPath("$.valeurAchatDuBien").value(DEFAULT_VALEUR_ACHAT_DU_BIEN.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMandat() throws Exception {
        // Get the mandat
        restMandatMockMvc.perform(get("/api/mandats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMandat() throws Exception {
        // Initialize the database
        mandatRepository.saveAndFlush(mandat);
        int databaseSizeBeforeUpdate = mandatRepository.findAll().size();

        // Update the mandat
        Mandat updatedMandat = mandatRepository.findOne(mandat.getId());
        updatedMandat
            .isActif(UPDATED_IS_ACTIF)
            .datedebutjuridique(UPDATED_DATEDEBUTJURIDIQUE)
            .datefinjuridique(UPDATED_DATEFINJURIDIQUE)
            .isValidated(UPDATED_IS_VALIDATED)
            .validationDate(UPDATED_VALIDATION_DATE)
            .isRejected(UPDATED_IS_REJECTED)
            .refusalDate(UPDATED_REFUSAL_DATE)
            .valeurAchatDuBien(UPDATED_VALEUR_ACHAT_DU_BIEN);

        restMandatMockMvc.perform(put("/api/mandats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMandat)))
            .andExpect(status().isOk());

        // Validate the Mandat in the database
        List<Mandat> mandatList = mandatRepository.findAll();
        assertThat(mandatList).hasSize(databaseSizeBeforeUpdate);
        Mandat testMandat = mandatList.get(mandatList.size() - 1);
        assertThat(testMandat.isIsActif()).isEqualTo(UPDATED_IS_ACTIF);
        assertThat(testMandat.isDatedebutjuridique()).isEqualTo(UPDATED_DATEDEBUTJURIDIQUE);
        assertThat(testMandat.getDatefinjuridique()).isEqualTo(UPDATED_DATEFINJURIDIQUE);
        assertThat(testMandat.isIsValidated()).isEqualTo(UPDATED_IS_VALIDATED);
        assertThat(testMandat.getValidationDate()).isEqualTo(UPDATED_VALIDATION_DATE);
        assertThat(testMandat.isIsRejected()).isEqualTo(UPDATED_IS_REJECTED);
        assertThat(testMandat.getRefusalDate()).isEqualTo(UPDATED_REFUSAL_DATE);
        assertThat(testMandat.getValeurAchatDuBien()).isEqualTo(UPDATED_VALEUR_ACHAT_DU_BIEN);
    }

    @Test
    @Transactional
    public void updateNonExistingMandat() throws Exception {
        int databaseSizeBeforeUpdate = mandatRepository.findAll().size();

        // Create the Mandat

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMandatMockMvc.perform(put("/api/mandats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mandat)))
            .andExpect(status().isCreated());

        // Validate the Mandat in the database
        List<Mandat> mandatList = mandatRepository.findAll();
        assertThat(mandatList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMandat() throws Exception {
        // Initialize the database
        mandatRepository.saveAndFlush(mandat);
        int databaseSizeBeforeDelete = mandatRepository.findAll().size();

        // Get the mandat
        restMandatMockMvc.perform(delete("/api/mandats/{id}", mandat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Mandat> mandatList = mandatRepository.findAll();
        assertThat(mandatList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mandat.class);
        Mandat mandat1 = new Mandat();
        mandat1.setId(1L);
        Mandat mandat2 = new Mandat();
        mandat2.setId(mandat1.getId());
        assertThat(mandat1).isEqualTo(mandat2);
        mandat2.setId(2L);
        assertThat(mandat1).isNotEqualTo(mandat2);
        mandat1.setId(null);
        assertThat(mandat1).isNotEqualTo(mandat2);
    }
}
