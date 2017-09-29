package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.Profil;
import klevalto.repository.ProfilRepository;
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
 * Test class for the ProfilResource REST controller.
 *
 * @see ProfilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class ProfilResourceIntTest {

    private static final Boolean DEFAULT_IS_ACTIF = false;
    private static final Boolean UPDATED_IS_ACTIF = true;

    private static final String DEFAULT_IDENTIFIANT_PROSPECT_LOCATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_PROSPECT_LOCATAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_LOCATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_LOCATAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_BAILLEUR = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_BAILLEUR = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_BAILLEUR_RECHERCHE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_BAILLEUR_RECHERCHE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_PERSONNEL_SERGIC = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_PERSONNEL_SERGIC = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_COPROPRIETAIRE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_COPROPRIETAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_INTERVENANT = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_INTERVENANT = "BBBBBBBBBB";

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfilMockMvc;

    private Profil profil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilResource profilResource = new ProfilResource(profilRepository);
        this.restProfilMockMvc = MockMvcBuilders.standaloneSetup(profilResource)
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
    public static Profil createEntity(EntityManager em) {
        Profil profil = new Profil()
            .isActif(DEFAULT_IS_ACTIF)
            .identifiantProspectLocataire(DEFAULT_IDENTIFIANT_PROSPECT_LOCATAIRE)
            .identifiantLocataire(DEFAULT_IDENTIFIANT_LOCATAIRE)
            .identifiantBailleur(DEFAULT_IDENTIFIANT_BAILLEUR)
            .identifiantBailleurRecherche(DEFAULT_IDENTIFIANT_BAILLEUR_RECHERCHE)
            .identifiantPersonnelSergic(DEFAULT_IDENTIFIANT_PERSONNEL_SERGIC)
            .identifiantCoproprietaire(DEFAULT_IDENTIFIANT_COPROPRIETAIRE)
            .identifiantProprietaireLocationSaisonniere(DEFAULT_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE)
            .identifiantIntervenant(DEFAULT_IDENTIFIANT_INTERVENANT);
        return profil;
    }

    @Before
    public void initTest() {
        profil = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfil() throws Exception {
        int databaseSizeBeforeCreate = profilRepository.findAll().size();

        // Create the Profil
        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profil)))
            .andExpect(status().isCreated());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeCreate + 1);
        Profil testProfil = profilList.get(profilList.size() - 1);
        assertThat(testProfil.isIsActif()).isEqualTo(DEFAULT_IS_ACTIF);
        assertThat(testProfil.getIdentifiantProspectLocataire()).isEqualTo(DEFAULT_IDENTIFIANT_PROSPECT_LOCATAIRE);
        assertThat(testProfil.getIdentifiantLocataire()).isEqualTo(DEFAULT_IDENTIFIANT_LOCATAIRE);
        assertThat(testProfil.getIdentifiantBailleur()).isEqualTo(DEFAULT_IDENTIFIANT_BAILLEUR);
        assertThat(testProfil.getIdentifiantBailleurRecherche()).isEqualTo(DEFAULT_IDENTIFIANT_BAILLEUR_RECHERCHE);
        assertThat(testProfil.getIdentifiantPersonnelSergic()).isEqualTo(DEFAULT_IDENTIFIANT_PERSONNEL_SERGIC);
        assertThat(testProfil.getIdentifiantCoproprietaire()).isEqualTo(DEFAULT_IDENTIFIANT_COPROPRIETAIRE);
        assertThat(testProfil.getIdentifiantProprietaireLocationSaisonniere()).isEqualTo(DEFAULT_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE);
        assertThat(testProfil.getIdentifiantIntervenant()).isEqualTo(DEFAULT_IDENTIFIANT_INTERVENANT);
    }

    @Test
    @Transactional
    public void createProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilRepository.findAll().size();

        // Create the Profil with an existing ID
        profil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profil)))
            .andExpect(status().isBadRequest());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProfils() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        // Get all the profilList
        restProfilMockMvc.perform(get("/api/profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profil.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActif").value(hasItem(DEFAULT_IS_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].identifiantProspectLocataire").value(hasItem(DEFAULT_IDENTIFIANT_PROSPECT_LOCATAIRE.toString())))
            .andExpect(jsonPath("$.[*].identifiantLocataire").value(hasItem(DEFAULT_IDENTIFIANT_LOCATAIRE.toString())))
            .andExpect(jsonPath("$.[*].identifiantBailleur").value(hasItem(DEFAULT_IDENTIFIANT_BAILLEUR.toString())))
            .andExpect(jsonPath("$.[*].identifiantBailleurRecherche").value(hasItem(DEFAULT_IDENTIFIANT_BAILLEUR_RECHERCHE.toString())))
            .andExpect(jsonPath("$.[*].identifiantPersonnelSergic").value(hasItem(DEFAULT_IDENTIFIANT_PERSONNEL_SERGIC.toString())))
            .andExpect(jsonPath("$.[*].identifiantCoproprietaire").value(hasItem(DEFAULT_IDENTIFIANT_COPROPRIETAIRE.toString())))
            .andExpect(jsonPath("$.[*].identifiantProprietaireLocationSaisonniere").value(hasItem(DEFAULT_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE.toString())))
            .andExpect(jsonPath("$.[*].identifiantIntervenant").value(hasItem(DEFAULT_IDENTIFIANT_INTERVENANT.toString())));
    }

    @Test
    @Transactional
    public void getProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        // Get the profil
        restProfilMockMvc.perform(get("/api/profils/{id}", profil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profil.getId().intValue()))
            .andExpect(jsonPath("$.isActif").value(DEFAULT_IS_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.identifiantProspectLocataire").value(DEFAULT_IDENTIFIANT_PROSPECT_LOCATAIRE.toString()))
            .andExpect(jsonPath("$.identifiantLocataire").value(DEFAULT_IDENTIFIANT_LOCATAIRE.toString()))
            .andExpect(jsonPath("$.identifiantBailleur").value(DEFAULT_IDENTIFIANT_BAILLEUR.toString()))
            .andExpect(jsonPath("$.identifiantBailleurRecherche").value(DEFAULT_IDENTIFIANT_BAILLEUR_RECHERCHE.toString()))
            .andExpect(jsonPath("$.identifiantPersonnelSergic").value(DEFAULT_IDENTIFIANT_PERSONNEL_SERGIC.toString()))
            .andExpect(jsonPath("$.identifiantCoproprietaire").value(DEFAULT_IDENTIFIANT_COPROPRIETAIRE.toString()))
            .andExpect(jsonPath("$.identifiantProprietaireLocationSaisonniere").value(DEFAULT_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE.toString()))
            .andExpect(jsonPath("$.identifiantIntervenant").value(DEFAULT_IDENTIFIANT_INTERVENANT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProfil() throws Exception {
        // Get the profil
        restProfilMockMvc.perform(get("/api/profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);
        int databaseSizeBeforeUpdate = profilRepository.findAll().size();

        // Update the profil
        Profil updatedProfil = profilRepository.findOne(profil.getId());
        updatedProfil
            .isActif(UPDATED_IS_ACTIF)
            .identifiantProspectLocataire(UPDATED_IDENTIFIANT_PROSPECT_LOCATAIRE)
            .identifiantLocataire(UPDATED_IDENTIFIANT_LOCATAIRE)
            .identifiantBailleur(UPDATED_IDENTIFIANT_BAILLEUR)
            .identifiantBailleurRecherche(UPDATED_IDENTIFIANT_BAILLEUR_RECHERCHE)
            .identifiantPersonnelSergic(UPDATED_IDENTIFIANT_PERSONNEL_SERGIC)
            .identifiantCoproprietaire(UPDATED_IDENTIFIANT_COPROPRIETAIRE)
            .identifiantProprietaireLocationSaisonniere(UPDATED_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE)
            .identifiantIntervenant(UPDATED_IDENTIFIANT_INTERVENANT);

        restProfilMockMvc.perform(put("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfil)))
            .andExpect(status().isOk());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeUpdate);
        Profil testProfil = profilList.get(profilList.size() - 1);
        assertThat(testProfil.isIsActif()).isEqualTo(UPDATED_IS_ACTIF);
        assertThat(testProfil.getIdentifiantProspectLocataire()).isEqualTo(UPDATED_IDENTIFIANT_PROSPECT_LOCATAIRE);
        assertThat(testProfil.getIdentifiantLocataire()).isEqualTo(UPDATED_IDENTIFIANT_LOCATAIRE);
        assertThat(testProfil.getIdentifiantBailleur()).isEqualTo(UPDATED_IDENTIFIANT_BAILLEUR);
        assertThat(testProfil.getIdentifiantBailleurRecherche()).isEqualTo(UPDATED_IDENTIFIANT_BAILLEUR_RECHERCHE);
        assertThat(testProfil.getIdentifiantPersonnelSergic()).isEqualTo(UPDATED_IDENTIFIANT_PERSONNEL_SERGIC);
        assertThat(testProfil.getIdentifiantCoproprietaire()).isEqualTo(UPDATED_IDENTIFIANT_COPROPRIETAIRE);
        assertThat(testProfil.getIdentifiantProprietaireLocationSaisonniere()).isEqualTo(UPDATED_IDENTIFIANT_PROPRIETAIRE_LOCATION_SAISONNIERE);
        assertThat(testProfil.getIdentifiantIntervenant()).isEqualTo(UPDATED_IDENTIFIANT_INTERVENANT);
    }

    @Test
    @Transactional
    public void updateNonExistingProfil() throws Exception {
        int databaseSizeBeforeUpdate = profilRepository.findAll().size();

        // Create the Profil

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProfilMockMvc.perform(put("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profil)))
            .andExpect(status().isCreated());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);
        int databaseSizeBeforeDelete = profilRepository.findAll().size();

        // Get the profil
        restProfilMockMvc.perform(delete("/api/profils/{id}", profil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Profil.class);
        Profil profil1 = new Profil();
        profil1.setId(1L);
        Profil profil2 = new Profil();
        profil2.setId(profil1.getId());
        assertThat(profil1).isEqualTo(profil2);
        profil2.setId(2L);
        assertThat(profil1).isNotEqualTo(profil2);
        profil1.setId(null);
        assertThat(profil1).isNotEqualTo(profil2);
    }
}
