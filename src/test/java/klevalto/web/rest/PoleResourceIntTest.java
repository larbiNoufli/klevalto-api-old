package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.Pole;
import klevalto.repository.PoleRepository;
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

/**
 * Test class for the PoleResource REST controller.
 *
 * @see PoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class PoleResourceIntTest {

    private static final String DEFAULT_CODE_POLE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POLE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_VOLET_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_VOLET_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_VOLET_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_VOLET_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_VOLET_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_VOLET_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_VOLET_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_VOLET_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_VOLET_5 = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_VOLET_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_VOLET_6 = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_VOLET_6 = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_VOLET_7 = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_VOLET_7 = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTIF = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTIF = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    @Autowired
    private PoleRepository poleRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPoleMockMvc;

    private Pole pole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PoleResource poleResource = new PoleResource(poleRepository);
        this.restPoleMockMvc = MockMvcBuilders.standaloneSetup(poleResource)
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
    public static Pole createEntity(EntityManager em) {
        Pole pole = new Pole()
            .codePole(DEFAULT_CODE_POLE)
            .libelle(DEFAULT_LIBELLE)
            .adresseVolet1(DEFAULT_ADRESSE_VOLET_1)
            .adresseVolet2(DEFAULT_ADRESSE_VOLET_2)
            .adresseVolet3(DEFAULT_ADRESSE_VOLET_3)
            .adresseVolet4(DEFAULT_ADRESSE_VOLET_4)
            .adresseVolet5(DEFAULT_ADRESSE_VOLET_5)
            .adresseVolet6(DEFAULT_ADRESSE_VOLET_6)
            .adresseVolet7(DEFAULT_ADRESSE_VOLET_7)
            .codePostal(DEFAULT_CODE_POSTAL)
            .descriptif(DEFAULT_DESCRIPTIF)
            .ville(DEFAULT_VILLE);
        return pole;
    }

    @Before
    public void initTest() {
        pole = createEntity(em);
    }

    @Test
    @Transactional
    public void createPole() throws Exception {
        int databaseSizeBeforeCreate = poleRepository.findAll().size();

        // Create the Pole
        restPoleMockMvc.perform(post("/api/poles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pole)))
            .andExpect(status().isCreated());

        // Validate the Pole in the database
        List<Pole> poleList = poleRepository.findAll();
        assertThat(poleList).hasSize(databaseSizeBeforeCreate + 1);
        Pole testPole = poleList.get(poleList.size() - 1);
        assertThat(testPole.getCodePole()).isEqualTo(DEFAULT_CODE_POLE);
        assertThat(testPole.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testPole.getAdresseVolet1()).isEqualTo(DEFAULT_ADRESSE_VOLET_1);
        assertThat(testPole.getAdresseVolet2()).isEqualTo(DEFAULT_ADRESSE_VOLET_2);
        assertThat(testPole.getAdresseVolet3()).isEqualTo(DEFAULT_ADRESSE_VOLET_3);
        assertThat(testPole.getAdresseVolet4()).isEqualTo(DEFAULT_ADRESSE_VOLET_4);
        assertThat(testPole.getAdresseVolet5()).isEqualTo(DEFAULT_ADRESSE_VOLET_5);
        assertThat(testPole.getAdresseVolet6()).isEqualTo(DEFAULT_ADRESSE_VOLET_6);
        assertThat(testPole.getAdresseVolet7()).isEqualTo(DEFAULT_ADRESSE_VOLET_7);
        assertThat(testPole.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testPole.getDescriptif()).isEqualTo(DEFAULT_DESCRIPTIF);
        assertThat(testPole.getVille()).isEqualTo(DEFAULT_VILLE);
    }

    @Test
    @Transactional
    public void createPoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = poleRepository.findAll().size();

        // Create the Pole with an existing ID
        pole.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPoleMockMvc.perform(post("/api/poles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pole)))
            .andExpect(status().isBadRequest());

        // Validate the Pole in the database
        List<Pole> poleList = poleRepository.findAll();
        assertThat(poleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPoles() throws Exception {
        // Initialize the database
        poleRepository.saveAndFlush(pole);

        // Get all the poleList
        restPoleMockMvc.perform(get("/api/poles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pole.getId().intValue())))
            .andExpect(jsonPath("$.[*].codePole").value(hasItem(DEFAULT_CODE_POLE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].adresseVolet1").value(hasItem(DEFAULT_ADRESSE_VOLET_1.toString())))
            .andExpect(jsonPath("$.[*].adresseVolet2").value(hasItem(DEFAULT_ADRESSE_VOLET_2.toString())))
            .andExpect(jsonPath("$.[*].adresseVolet3").value(hasItem(DEFAULT_ADRESSE_VOLET_3.toString())))
            .andExpect(jsonPath("$.[*].adresseVolet4").value(hasItem(DEFAULT_ADRESSE_VOLET_4.toString())))
            .andExpect(jsonPath("$.[*].adresseVolet5").value(hasItem(DEFAULT_ADRESSE_VOLET_5.toString())))
            .andExpect(jsonPath("$.[*].adresseVolet6").value(hasItem(DEFAULT_ADRESSE_VOLET_6.toString())))
            .andExpect(jsonPath("$.[*].adresseVolet7").value(hasItem(DEFAULT_ADRESSE_VOLET_7.toString())))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL.toString())))
            .andExpect(jsonPath("$.[*].descriptif").value(hasItem(DEFAULT_DESCRIPTIF.toString())))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE.toString())));
    }

    @Test
    @Transactional
    public void getPole() throws Exception {
        // Initialize the database
        poleRepository.saveAndFlush(pole);

        // Get the pole
        restPoleMockMvc.perform(get("/api/poles/{id}", pole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pole.getId().intValue()))
            .andExpect(jsonPath("$.codePole").value(DEFAULT_CODE_POLE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.adresseVolet1").value(DEFAULT_ADRESSE_VOLET_1.toString()))
            .andExpect(jsonPath("$.adresseVolet2").value(DEFAULT_ADRESSE_VOLET_2.toString()))
            .andExpect(jsonPath("$.adresseVolet3").value(DEFAULT_ADRESSE_VOLET_3.toString()))
            .andExpect(jsonPath("$.adresseVolet4").value(DEFAULT_ADRESSE_VOLET_4.toString()))
            .andExpect(jsonPath("$.adresseVolet5").value(DEFAULT_ADRESSE_VOLET_5.toString()))
            .andExpect(jsonPath("$.adresseVolet6").value(DEFAULT_ADRESSE_VOLET_6.toString()))
            .andExpect(jsonPath("$.adresseVolet7").value(DEFAULT_ADRESSE_VOLET_7.toString()))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL.toString()))
            .andExpect(jsonPath("$.descriptif").value(DEFAULT_DESCRIPTIF.toString()))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPole() throws Exception {
        // Get the pole
        restPoleMockMvc.perform(get("/api/poles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePole() throws Exception {
        // Initialize the database
        poleRepository.saveAndFlush(pole);
        int databaseSizeBeforeUpdate = poleRepository.findAll().size();

        // Update the pole
        Pole updatedPole = poleRepository.findOne(pole.getId());
        updatedPole
            .codePole(UPDATED_CODE_POLE)
            .libelle(UPDATED_LIBELLE)
            .adresseVolet1(UPDATED_ADRESSE_VOLET_1)
            .adresseVolet2(UPDATED_ADRESSE_VOLET_2)
            .adresseVolet3(UPDATED_ADRESSE_VOLET_3)
            .adresseVolet4(UPDATED_ADRESSE_VOLET_4)
            .adresseVolet5(UPDATED_ADRESSE_VOLET_5)
            .adresseVolet6(UPDATED_ADRESSE_VOLET_6)
            .adresseVolet7(UPDATED_ADRESSE_VOLET_7)
            .codePostal(UPDATED_CODE_POSTAL)
            .descriptif(UPDATED_DESCRIPTIF)
            .ville(UPDATED_VILLE);

        restPoleMockMvc.perform(put("/api/poles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPole)))
            .andExpect(status().isOk());

        // Validate the Pole in the database
        List<Pole> poleList = poleRepository.findAll();
        assertThat(poleList).hasSize(databaseSizeBeforeUpdate);
        Pole testPole = poleList.get(poleList.size() - 1);
        assertThat(testPole.getCodePole()).isEqualTo(UPDATED_CODE_POLE);
        assertThat(testPole.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testPole.getAdresseVolet1()).isEqualTo(UPDATED_ADRESSE_VOLET_1);
        assertThat(testPole.getAdresseVolet2()).isEqualTo(UPDATED_ADRESSE_VOLET_2);
        assertThat(testPole.getAdresseVolet3()).isEqualTo(UPDATED_ADRESSE_VOLET_3);
        assertThat(testPole.getAdresseVolet4()).isEqualTo(UPDATED_ADRESSE_VOLET_4);
        assertThat(testPole.getAdresseVolet5()).isEqualTo(UPDATED_ADRESSE_VOLET_5);
        assertThat(testPole.getAdresseVolet6()).isEqualTo(UPDATED_ADRESSE_VOLET_6);
        assertThat(testPole.getAdresseVolet7()).isEqualTo(UPDATED_ADRESSE_VOLET_7);
        assertThat(testPole.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testPole.getDescriptif()).isEqualTo(UPDATED_DESCRIPTIF);
        assertThat(testPole.getVille()).isEqualTo(UPDATED_VILLE);
    }

    @Test
    @Transactional
    public void updateNonExistingPole() throws Exception {
        int databaseSizeBeforeUpdate = poleRepository.findAll().size();

        // Create the Pole

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPoleMockMvc.perform(put("/api/poles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pole)))
            .andExpect(status().isCreated());

        // Validate the Pole in the database
        List<Pole> poleList = poleRepository.findAll();
        assertThat(poleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePole() throws Exception {
        // Initialize the database
        poleRepository.saveAndFlush(pole);
        int databaseSizeBeforeDelete = poleRepository.findAll().size();

        // Get the pole
        restPoleMockMvc.perform(delete("/api/poles/{id}", pole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pole> poleList = poleRepository.findAll();
        assertThat(poleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pole.class);
        Pole pole1 = new Pole();
        pole1.setId(1L);
        Pole pole2 = new Pole();
        pole2.setId(pole1.getId());
        assertThat(pole1).isEqualTo(pole2);
        pole2.setId(2L);
        assertThat(pole1).isNotEqualTo(pole2);
        pole1.setId(null);
        assertThat(pole1).isNotEqualTo(pole2);
    }
}
