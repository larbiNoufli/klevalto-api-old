package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.RibTiers;
import klevalto.repository.RibTiersRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import klevalto.domain.enumeration.Civilite;
/**
 * Test class for the RibTiersResource REST controller.
 *
 * @see RibTiersResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class RibTiersResourceIntTest {

    private static final String DEFAULT_DOMICILIATION = "AAAAAAAAAA";
    private static final String UPDATED_DOMICILIATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CODE_BANQUE = 1;
    private static final Integer UPDATED_CODE_BANQUE = 2;

    private static final Integer DEFAULT_CODE_GUICHET = 1;
    private static final Integer UPDATED_CODE_GUICHET = 2;

    private static final String DEFAULT_NUM_COMPTE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_COMPTE = "BBBBBBBBBB";

    private static final String DEFAULT_CLE_RIB = "AAAAAAAAAA";
    private static final String UPDATED_CLE_RIB = "BBBBBBBBBB";

    private static final String DEFAULT_IBAN = "AAAAAAAAAA";
    private static final String UPDATED_IBAN = "BBBBBBBBBB";

    private static final String DEFAULT_BIC = "AAAAAAAAAA";
    private static final String UPDATED_BIC = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Civilite DEFAULT_CIVILITE = Civilite.M;
    private static final Civilite UPDATED_CIVILITE = Civilite.MME;

    private static final String DEFAULT_TITULAIRE_DU_COMPTE = "AAAAAAAAAA";
    private static final String UPDATED_TITULAIRE_DU_COMPTE = "BBBBBBBBBB";

    private static final Long DEFAULT_NUM_INTERNE_MAYA = 1L;
    private static final Long UPDATED_NUM_INTERNE_MAYA = 2L;

    @Autowired
    private RibTiersRepository ribTiersRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRibTiersMockMvc;

    private RibTiers ribTiers;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RibTiersResource ribTiersResource = new RibTiersResource(ribTiersRepository);
        this.restRibTiersMockMvc = MockMvcBuilders.standaloneSetup(ribTiersResource)
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
    public static RibTiers createEntity(EntityManager em) {
        RibTiers ribTiers = new RibTiers()
            .domiciliation(DEFAULT_DOMICILIATION)
            .codeBanque(DEFAULT_CODE_BANQUE)
            .codeGuichet(DEFAULT_CODE_GUICHET)
            .numCompte(DEFAULT_NUM_COMPTE)
            .cleRib(DEFAULT_CLE_RIB)
            .iban(DEFAULT_IBAN)
            .bic(DEFAULT_BIC)
            .libelle(DEFAULT_LIBELLE)
            .civilite(DEFAULT_CIVILITE)
            .titulaireDuCompte(DEFAULT_TITULAIRE_DU_COMPTE)
            .numInterneMaya(DEFAULT_NUM_INTERNE_MAYA);
        return ribTiers;
    }

    @Before
    public void initTest() {
        ribTiers = createEntity(em);
    }

    @Test
    @Transactional
    public void createRibTiers() throws Exception {
        int databaseSizeBeforeCreate = ribTiersRepository.findAll().size();

        // Create the RibTiers
        restRibTiersMockMvc.perform(post("/api/rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ribTiers)))
            .andExpect(status().isCreated());

        // Validate the RibTiers in the database
        List<RibTiers> ribTiersList = ribTiersRepository.findAll();
        assertThat(ribTiersList).hasSize(databaseSizeBeforeCreate + 1);
        RibTiers testRibTiers = ribTiersList.get(ribTiersList.size() - 1);
        assertThat(testRibTiers.getDomiciliation()).isEqualTo(DEFAULT_DOMICILIATION);
        assertThat(testRibTiers.getCodeBanque()).isEqualTo(DEFAULT_CODE_BANQUE);
        assertThat(testRibTiers.getCodeGuichet()).isEqualTo(DEFAULT_CODE_GUICHET);
        assertThat(testRibTiers.getNumCompte()).isEqualTo(DEFAULT_NUM_COMPTE);
        assertThat(testRibTiers.getCleRib()).isEqualTo(DEFAULT_CLE_RIB);
        assertThat(testRibTiers.getIban()).isEqualTo(DEFAULT_IBAN);
        assertThat(testRibTiers.getBic()).isEqualTo(DEFAULT_BIC);
        assertThat(testRibTiers.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testRibTiers.getCivilite()).isEqualTo(DEFAULT_CIVILITE);
        assertThat(testRibTiers.getTitulaireDuCompte()).isEqualTo(DEFAULT_TITULAIRE_DU_COMPTE);
        assertThat(testRibTiers.getNumInterneMaya()).isEqualTo(DEFAULT_NUM_INTERNE_MAYA);
    }

    @Test
    @Transactional
    public void createRibTiersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ribTiersRepository.findAll().size();

        // Create the RibTiers with an existing ID
        ribTiers.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRibTiersMockMvc.perform(post("/api/rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ribTiers)))
            .andExpect(status().isBadRequest());

        // Validate the RibTiers in the database
        List<RibTiers> ribTiersList = ribTiersRepository.findAll();
        assertThat(ribTiersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRibTiers() throws Exception {
        // Initialize the database
        ribTiersRepository.saveAndFlush(ribTiers);

        // Get all the ribTiersList
        restRibTiersMockMvc.perform(get("/api/rib-tiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ribTiers.getId().intValue())))
            .andExpect(jsonPath("$.[*].domiciliation").value(hasItem(DEFAULT_DOMICILIATION.toString())))
            .andExpect(jsonPath("$.[*].codeBanque").value(hasItem(DEFAULT_CODE_BANQUE)))
            .andExpect(jsonPath("$.[*].codeGuichet").value(hasItem(DEFAULT_CODE_GUICHET)))
            .andExpect(jsonPath("$.[*].numCompte").value(hasItem(DEFAULT_NUM_COMPTE.toString())))
            .andExpect(jsonPath("$.[*].cleRib").value(hasItem(DEFAULT_CLE_RIB.toString())))
            .andExpect(jsonPath("$.[*].iban").value(hasItem(DEFAULT_IBAN.toString())))
            .andExpect(jsonPath("$.[*].bic").value(hasItem(DEFAULT_BIC.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].civilite").value(hasItem(DEFAULT_CIVILITE.toString())))
            .andExpect(jsonPath("$.[*].titulaireDuCompte").value(hasItem(DEFAULT_TITULAIRE_DU_COMPTE.toString())))
            .andExpect(jsonPath("$.[*].numInterneMaya").value(hasItem(DEFAULT_NUM_INTERNE_MAYA.intValue())));
    }

    @Test
    @Transactional
    public void getRibTiers() throws Exception {
        // Initialize the database
        ribTiersRepository.saveAndFlush(ribTiers);

        // Get the ribTiers
        restRibTiersMockMvc.perform(get("/api/rib-tiers/{id}", ribTiers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ribTiers.getId().intValue()))
            .andExpect(jsonPath("$.domiciliation").value(DEFAULT_DOMICILIATION.toString()))
            .andExpect(jsonPath("$.codeBanque").value(DEFAULT_CODE_BANQUE))
            .andExpect(jsonPath("$.codeGuichet").value(DEFAULT_CODE_GUICHET))
            .andExpect(jsonPath("$.numCompte").value(DEFAULT_NUM_COMPTE.toString()))
            .andExpect(jsonPath("$.cleRib").value(DEFAULT_CLE_RIB.toString()))
            .andExpect(jsonPath("$.iban").value(DEFAULT_IBAN.toString()))
            .andExpect(jsonPath("$.bic").value(DEFAULT_BIC.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.civilite").value(DEFAULT_CIVILITE.toString()))
            .andExpect(jsonPath("$.titulaireDuCompte").value(DEFAULT_TITULAIRE_DU_COMPTE.toString()))
            .andExpect(jsonPath("$.numInterneMaya").value(DEFAULT_NUM_INTERNE_MAYA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRibTiers() throws Exception {
        // Get the ribTiers
        restRibTiersMockMvc.perform(get("/api/rib-tiers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRibTiers() throws Exception {
        // Initialize the database
        ribTiersRepository.saveAndFlush(ribTiers);
        int databaseSizeBeforeUpdate = ribTiersRepository.findAll().size();

        // Update the ribTiers
        RibTiers updatedRibTiers = ribTiersRepository.findOne(ribTiers.getId());
        updatedRibTiers
            .domiciliation(UPDATED_DOMICILIATION)
            .codeBanque(UPDATED_CODE_BANQUE)
            .codeGuichet(UPDATED_CODE_GUICHET)
            .numCompte(UPDATED_NUM_COMPTE)
            .cleRib(UPDATED_CLE_RIB)
            .iban(UPDATED_IBAN)
            .bic(UPDATED_BIC)
            .libelle(UPDATED_LIBELLE)
            .civilite(UPDATED_CIVILITE)
            .titulaireDuCompte(UPDATED_TITULAIRE_DU_COMPTE)
            .numInterneMaya(UPDATED_NUM_INTERNE_MAYA);

        restRibTiersMockMvc.perform(put("/api/rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRibTiers)))
            .andExpect(status().isOk());

        // Validate the RibTiers in the database
        List<RibTiers> ribTiersList = ribTiersRepository.findAll();
        assertThat(ribTiersList).hasSize(databaseSizeBeforeUpdate);
        RibTiers testRibTiers = ribTiersList.get(ribTiersList.size() - 1);
        assertThat(testRibTiers.getDomiciliation()).isEqualTo(UPDATED_DOMICILIATION);
        assertThat(testRibTiers.getCodeBanque()).isEqualTo(UPDATED_CODE_BANQUE);
        assertThat(testRibTiers.getCodeGuichet()).isEqualTo(UPDATED_CODE_GUICHET);
        assertThat(testRibTiers.getNumCompte()).isEqualTo(UPDATED_NUM_COMPTE);
        assertThat(testRibTiers.getCleRib()).isEqualTo(UPDATED_CLE_RIB);
        assertThat(testRibTiers.getIban()).isEqualTo(UPDATED_IBAN);
        assertThat(testRibTiers.getBic()).isEqualTo(UPDATED_BIC);
        assertThat(testRibTiers.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testRibTiers.getCivilite()).isEqualTo(UPDATED_CIVILITE);
        assertThat(testRibTiers.getTitulaireDuCompte()).isEqualTo(UPDATED_TITULAIRE_DU_COMPTE);
        assertThat(testRibTiers.getNumInterneMaya()).isEqualTo(UPDATED_NUM_INTERNE_MAYA);
    }

    @Test
    @Transactional
    public void updateNonExistingRibTiers() throws Exception {
        int databaseSizeBeforeUpdate = ribTiersRepository.findAll().size();

        // Create the RibTiers

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRibTiersMockMvc.perform(put("/api/rib-tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ribTiers)))
            .andExpect(status().isCreated());

        // Validate the RibTiers in the database
        List<RibTiers> ribTiersList = ribTiersRepository.findAll();
        assertThat(ribTiersList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRibTiers() throws Exception {
        // Initialize the database
        ribTiersRepository.saveAndFlush(ribTiers);
        int databaseSizeBeforeDelete = ribTiersRepository.findAll().size();

        // Get the ribTiers
        restRibTiersMockMvc.perform(delete("/api/rib-tiers/{id}", ribTiers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RibTiers> ribTiersList = ribTiersRepository.findAll();
        assertThat(ribTiersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RibTiers.class);
        RibTiers ribTiers1 = new RibTiers();
        ribTiers1.setId(1L);
        RibTiers ribTiers2 = new RibTiers();
        ribTiers2.setId(ribTiers1.getId());
        assertThat(ribTiers1).isEqualTo(ribTiers2);
        ribTiers2.setId(2L);
        assertThat(ribTiers1).isNotEqualTo(ribTiers2);
        ribTiers1.setId(null);
        assertThat(ribTiers1).isNotEqualTo(ribTiers2);
    }
}
