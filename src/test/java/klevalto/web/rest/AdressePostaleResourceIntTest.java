package klevalto.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import klevalto.domain.AdressePostale;
import klevalto.repository.AdressePostaleRepository;
import klevalto.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the AdressePostaleResource REST controller.
 *
 * @see AdressePostaleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class AdressePostaleResourceIntTest {

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_VILLE = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMERO_VOIE = 1L;
    private static final Long UPDATED_NUMERO_VOIE = 2L;

    private static final String DEFAULT_COMPLEMENT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENT_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_INSEE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_INSEE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    @Autowired
    private AdressePostaleRepository adressePostaleRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdressePostaleMockMvc;

    private AdressePostale adressePostale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdressePostaleResource adressePostaleResource = new AdressePostaleResource(adressePostaleRepository);
        this.restAdressePostaleMockMvc = MockMvcBuilders.standaloneSetup(adressePostaleResource)
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
    public static AdressePostale createEntity(EntityManager em) {
        AdressePostale adressePostale = new AdressePostale()
            .codePostal(DEFAULT_CODE_POSTAL)
            .nomVille(DEFAULT_NOM_VILLE)
            .numeroVoie(DEFAULT_NUMERO_VOIE)
            .complementAdresse(DEFAULT_COMPLEMENT_ADRESSE)
            .numINSEE(DEFAULT_NUM_INSEE)
            .pays(DEFAULT_PAYS);
        return adressePostale;
    }

    @Before
    public void initTest() {
        adressePostale = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdressePostale() throws Exception {
        int databaseSizeBeforeCreate = adressePostaleRepository.findAll().size();

        // Create the AdressePostale
        restAdressePostaleMockMvc.perform(post("/api/adresse-postales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adressePostale)))
            .andExpect(status().isCreated());

        // Validate the AdressePostale in the database
        List<AdressePostale> adressePostaleList = adressePostaleRepository.findAll();
        assertThat(adressePostaleList).hasSize(databaseSizeBeforeCreate + 1);
        AdressePostale testAdressePostale = adressePostaleList.get(adressePostaleList.size() - 1);
        assertThat(testAdressePostale.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testAdressePostale.getNomVille()).isEqualTo(DEFAULT_NOM_VILLE);
        assertThat(testAdressePostale.getNumeroVoie()).isEqualTo(DEFAULT_NUMERO_VOIE);
        assertThat(testAdressePostale.getComplementAdresse()).isEqualTo(DEFAULT_COMPLEMENT_ADRESSE);
        assertThat(testAdressePostale.getNumINSEE()).isEqualTo(DEFAULT_NUM_INSEE);
        assertThat(testAdressePostale.getPays()).isEqualTo(DEFAULT_PAYS);
    }

    @Test
    @Transactional
    public void createAdressePostaleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adressePostaleRepository.findAll().size();

        // Create the AdressePostale with an existing ID
        adressePostale.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdressePostaleMockMvc.perform(post("/api/adresse-postales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adressePostale)))
            .andExpect(status().isBadRequest());

        // Validate the AdressePostale in the database
        List<AdressePostale> adressePostaleList = adressePostaleRepository.findAll();
        assertThat(adressePostaleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdressePostales() throws Exception {
        // Initialize the database
        adressePostaleRepository.saveAndFlush(adressePostale);

        // Get all the adressePostaleList
        restAdressePostaleMockMvc.perform(get("/api/adresse-postales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adressePostale.getId().intValue())))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL.toString())))
            .andExpect(jsonPath("$.[*].nomVille").value(hasItem(DEFAULT_NOM_VILLE.toString())))
            .andExpect(jsonPath("$.[*].numeroVoie").value(hasItem(DEFAULT_NUMERO_VOIE.intValue())))
            .andExpect(jsonPath("$.[*].complementAdresse").value(hasItem(DEFAULT_COMPLEMENT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].numINSEE").value(hasItem(DEFAULT_NUM_INSEE.toString())))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS.toString())));
    }

    @Test
    @Transactional
    public void getAdressePostale() throws Exception {
        // Initialize the database
        adressePostaleRepository.saveAndFlush(adressePostale);

        // Get the adressePostale
        restAdressePostaleMockMvc.perform(get("/api/adresse-postales/{id}", adressePostale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adressePostale.getId().intValue()))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL.toString()))
            .andExpect(jsonPath("$.nomVille").value(DEFAULT_NOM_VILLE.toString()))
            .andExpect(jsonPath("$.numeroVoie").value(DEFAULT_NUMERO_VOIE.intValue()))
            .andExpect(jsonPath("$.complementAdresse").value(DEFAULT_COMPLEMENT_ADRESSE.toString()))
            .andExpect(jsonPath("$.numINSEE").value(DEFAULT_NUM_INSEE.toString()))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdressePostale() throws Exception {
        // Get the adressePostale
        restAdressePostaleMockMvc.perform(get("/api/adresse-postales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdressePostale() throws Exception {
        // Initialize the database
        adressePostaleRepository.saveAndFlush(adressePostale);
        int databaseSizeBeforeUpdate = adressePostaleRepository.findAll().size();

        // Update the adressePostale
        AdressePostale updatedAdressePostale = adressePostaleRepository.findOne(adressePostale.getId());
        updatedAdressePostale
            .codePostal(UPDATED_CODE_POSTAL)
            .nomVille(UPDATED_NOM_VILLE)
            .numeroVoie(UPDATED_NUMERO_VOIE)
            .complementAdresse(UPDATED_COMPLEMENT_ADRESSE)
            .numINSEE(UPDATED_NUM_INSEE)
            .pays(UPDATED_PAYS);

        restAdressePostaleMockMvc.perform(put("/api/adresse-postales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdressePostale)))
            .andExpect(status().isOk());

        // Validate the AdressePostale in the database
        List<AdressePostale> adressePostaleList = adressePostaleRepository.findAll();
        assertThat(adressePostaleList).hasSize(databaseSizeBeforeUpdate);
        AdressePostale testAdressePostale = adressePostaleList.get(adressePostaleList.size() - 1);
        assertThat(testAdressePostale.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testAdressePostale.getNomVille()).isEqualTo(UPDATED_NOM_VILLE);
        assertThat(testAdressePostale.getNumeroVoie()).isEqualTo(UPDATED_NUMERO_VOIE);
        assertThat(testAdressePostale.getComplementAdresse()).isEqualTo(UPDATED_COMPLEMENT_ADRESSE);
        assertThat(testAdressePostale.getNumINSEE()).isEqualTo(UPDATED_NUM_INSEE);
        assertThat(testAdressePostale.getPays()).isEqualTo(UPDATED_PAYS);
    }

    @Test
    @Transactional
    public void updateNonExistingAdressePostale() throws Exception {
        int databaseSizeBeforeUpdate = adressePostaleRepository.findAll().size();

        // Create the AdressePostale

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdressePostaleMockMvc.perform(put("/api/adresse-postales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adressePostale)))
            .andExpect(status().isCreated());

        // Validate the AdressePostale in the database
        List<AdressePostale> adressePostaleList = adressePostaleRepository.findAll();
        assertThat(adressePostaleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAdressePostale() throws Exception {
        // Initialize the database
        adressePostaleRepository.saveAndFlush(adressePostale);
        int databaseSizeBeforeDelete = adressePostaleRepository.findAll().size();

        // Get the adressePostale
        restAdressePostaleMockMvc.perform(delete("/api/adresse-postales/{id}", adressePostale.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdressePostale> adressePostaleList = adressePostaleRepository.findAll();
        assertThat(adressePostaleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdressePostale.class);
        AdressePostale adressePostale1 = new AdressePostale();
        adressePostale1.setId(1L);
        AdressePostale adressePostale2 = new AdressePostale();
        adressePostale2.setId(adressePostale1.getId());
        assertThat(adressePostale1).isEqualTo(adressePostale2);
        adressePostale2.setId(2L);
        assertThat(adressePostale1).isNotEqualTo(adressePostale2);
        adressePostale1.setId(null);
        assertThat(adressePostale1).isNotEqualTo(adressePostale2);
    }
}
