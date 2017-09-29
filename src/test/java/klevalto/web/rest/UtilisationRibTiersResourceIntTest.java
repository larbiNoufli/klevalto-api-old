package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.UtilisationRibTiers;
import klevalto.repository.UtilisationRibTiersRepository;
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
 * Test class for the UtilisationRibTiersResource REST controller.
 *
 * @see UtilisationRibTiersResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class UtilisationRibTiersResourceIntTest {

    private static final String DEFAULT_IDENTIFIANT_MAYA = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_MAYA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_JOUR_PRELEVEMENT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_JOUR_PRELEVEMENT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NUM_NATIONAL_EMETTEUR = "AAAAAAAAAA";
    private static final String UPDATED_NUM_NATIONAL_EMETTEUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_ORDRE = 1;
    private static final Integer UPDATED_NUMERO_ORDRE = 2;

    @Autowired
    private UtilisationRibTiersRepository utilisationRibTiersRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtilisationRibTiersMockMvc;

    private UtilisationRibTiers utilisationRibTiers;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtilisationRibTiersResource utilisationRibTiersResource = new UtilisationRibTiersResource(utilisationRibTiersRepository);
        this.restUtilisationRibTiersMockMvc = MockMvcBuilders.standaloneSetup(utilisationRibTiersResource)
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
    public static UtilisationRibTiers createEntity(EntityManager em) {
        UtilisationRibTiers utilisationRibTiers = new UtilisationRibTiers()
            .identifiantMaya(DEFAULT_IDENTIFIANT_MAYA)
            .jourPrelevement(DEFAULT_JOUR_PRELEVEMENT)
            .numNationalEmetteur(DEFAULT_NUM_NATIONAL_EMETTEUR)
            .numeroOrdre(DEFAULT_NUMERO_ORDRE);
        return utilisationRibTiers;
    }

    @Before
    public void initTest() {
        utilisationRibTiers = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisationRibTiers() throws Exception {
        int databaseSizeBeforeCreate = utilisationRibTiersRepository.findAll().size();

        // Create the UtilisationRibTiers
        restUtilisationRibTiersMockMvc.perform(post("/api/utilisation-rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisationRibTiers)))
            .andExpect(status().isCreated());

        // Validate the UtilisationRibTiers in the database
        List<UtilisationRibTiers> utilisationRibTiersList = utilisationRibTiersRepository.findAll();
        assertThat(utilisationRibTiersList).hasSize(databaseSizeBeforeCreate + 1);
        UtilisationRibTiers testUtilisationRibTiers = utilisationRibTiersList.get(utilisationRibTiersList.size() - 1);
        assertThat(testUtilisationRibTiers.getIdentifiantMaya()).isEqualTo(DEFAULT_IDENTIFIANT_MAYA);
        assertThat(testUtilisationRibTiers.getJourPrelevement()).isEqualTo(DEFAULT_JOUR_PRELEVEMENT);
        assertThat(testUtilisationRibTiers.getNumNationalEmetteur()).isEqualTo(DEFAULT_NUM_NATIONAL_EMETTEUR);
        assertThat(testUtilisationRibTiers.getNumeroOrdre()).isEqualTo(DEFAULT_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void createUtilisationRibTiersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisationRibTiersRepository.findAll().size();

        // Create the UtilisationRibTiers with an existing ID
        utilisationRibTiers.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisationRibTiersMockMvc.perform(post("/api/utilisation-rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisationRibTiers)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisationRibTiers in the database
        List<UtilisationRibTiers> utilisationRibTiersList = utilisationRibTiersRepository.findAll();
        assertThat(utilisationRibTiersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUtilisationRibTiers() throws Exception {
        // Initialize the database
        utilisationRibTiersRepository.saveAndFlush(utilisationRibTiers);

        // Get all the utilisationRibTiersList
        restUtilisationRibTiersMockMvc.perform(get("/api/utilisation-rib-tiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisationRibTiers.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifiantMaya").value(hasItem(DEFAULT_IDENTIFIANT_MAYA.toString())))
            .andExpect(jsonPath("$.[*].jourPrelevement").value(hasItem(sameInstant(DEFAULT_JOUR_PRELEVEMENT))))
            .andExpect(jsonPath("$.[*].numNationalEmetteur").value(hasItem(DEFAULT_NUM_NATIONAL_EMETTEUR.toString())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)));
    }

    @Test
    @Transactional
    public void getUtilisationRibTiers() throws Exception {
        // Initialize the database
        utilisationRibTiersRepository.saveAndFlush(utilisationRibTiers);

        // Get the utilisationRibTiers
        restUtilisationRibTiersMockMvc.perform(get("/api/utilisation-rib-tiers/{id}", utilisationRibTiers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utilisationRibTiers.getId().intValue()))
            .andExpect(jsonPath("$.identifiantMaya").value(DEFAULT_IDENTIFIANT_MAYA.toString()))
            .andExpect(jsonPath("$.jourPrelevement").value(sameInstant(DEFAULT_JOUR_PRELEVEMENT)))
            .andExpect(jsonPath("$.numNationalEmetteur").value(DEFAULT_NUM_NATIONAL_EMETTEUR.toString()))
            .andExpect(jsonPath("$.numeroOrdre").value(DEFAULT_NUMERO_ORDRE));
    }

    @Test
    @Transactional
    public void getNonExistingUtilisationRibTiers() throws Exception {
        // Get the utilisationRibTiers
        restUtilisationRibTiersMockMvc.perform(get("/api/utilisation-rib-tiers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisationRibTiers() throws Exception {
        // Initialize the database
        utilisationRibTiersRepository.saveAndFlush(utilisationRibTiers);
        int databaseSizeBeforeUpdate = utilisationRibTiersRepository.findAll().size();

        // Update the utilisationRibTiers
        UtilisationRibTiers updatedUtilisationRibTiers = utilisationRibTiersRepository.findOne(utilisationRibTiers.getId());
        updatedUtilisationRibTiers
            .identifiantMaya(UPDATED_IDENTIFIANT_MAYA)
            .jourPrelevement(UPDATED_JOUR_PRELEVEMENT)
            .numNationalEmetteur(UPDATED_NUM_NATIONAL_EMETTEUR)
            .numeroOrdre(UPDATED_NUMERO_ORDRE);

        restUtilisationRibTiersMockMvc.perform(put("/api/utilisation-rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUtilisationRibTiers)))
            .andExpect(status().isOk());

        // Validate the UtilisationRibTiers in the database
        List<UtilisationRibTiers> utilisationRibTiersList = utilisationRibTiersRepository.findAll();
        assertThat(utilisationRibTiersList).hasSize(databaseSizeBeforeUpdate);
        UtilisationRibTiers testUtilisationRibTiers = utilisationRibTiersList.get(utilisationRibTiersList.size() - 1);
        assertThat(testUtilisationRibTiers.getIdentifiantMaya()).isEqualTo(UPDATED_IDENTIFIANT_MAYA);
        assertThat(testUtilisationRibTiers.getJourPrelevement()).isEqualTo(UPDATED_JOUR_PRELEVEMENT);
        assertThat(testUtilisationRibTiers.getNumNationalEmetteur()).isEqualTo(UPDATED_NUM_NATIONAL_EMETTEUR);
        assertThat(testUtilisationRibTiers.getNumeroOrdre()).isEqualTo(UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisationRibTiers() throws Exception {
        int databaseSizeBeforeUpdate = utilisationRibTiersRepository.findAll().size();

        // Create the UtilisationRibTiers

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUtilisationRibTiersMockMvc.perform(put("/api/utilisation-rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisationRibTiers)))
            .andExpect(status().isCreated());

        // Validate the UtilisationRibTiers in the database
        List<UtilisationRibTiers> utilisationRibTiersList = utilisationRibTiersRepository.findAll();
        assertThat(utilisationRibTiersList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUtilisationRibTiers() throws Exception {
        // Initialize the database
        utilisationRibTiersRepository.saveAndFlush(utilisationRibTiers);
        int databaseSizeBeforeDelete = utilisationRibTiersRepository.findAll().size();

        // Get the utilisationRibTiers
        restUtilisationRibTiersMockMvc.perform(delete("/api/utilisation-rib-tiers/{id}", utilisationRibTiers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UtilisationRibTiers> utilisationRibTiersList = utilisationRibTiersRepository.findAll();
        assertThat(utilisationRibTiersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisationRibTiers.class);
        UtilisationRibTiers utilisationRibTiers1 = new UtilisationRibTiers();
        utilisationRibTiers1.setId(1L);
        UtilisationRibTiers utilisationRibTiers2 = new UtilisationRibTiers();
        utilisationRibTiers2.setId(utilisationRibTiers1.getId());
        assertThat(utilisationRibTiers1).isEqualTo(utilisationRibTiers2);
        utilisationRibTiers2.setId(2L);
        assertThat(utilisationRibTiers1).isNotEqualTo(utilisationRibTiers2);
        utilisationRibTiers1.setId(null);
        assertThat(utilisationRibTiers1).isNotEqualTo(utilisationRibTiers2);
    }
}
