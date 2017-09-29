package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.TypeBien;
import klevalto.repository.TypeBienRepository;
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
 * Test class for the TypeBienResource REST controller.
 *
 * @see TypeBienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class TypeBienResourceIntTest {

    private static final String DEFAULT_CODE_TYPE_LOT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TYPE_LOT = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_ANNONCE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_ANNONCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_HABITABLE = false;
    private static final Boolean UPDATED_IS_HABITABLE = true;

    private static final Integer DEFAULT_DUREE_VISITE_LOCATION = 1;
    private static final Integer UPDATED_DUREE_VISITE_LOCATION = 2;

    private static final Integer DEFAULT_DUREE_VISITE_VENTE = 1;
    private static final Integer UPDATED_DUREE_VISITE_VENTE = 2;

    private static final Integer DEFAULT_ORDRE_AFFICHAGE = 1;
    private static final Integer UPDATED_ORDRE_AFFICHAGE = 2;

    private static final Integer DEFAULT_IS_TYPE_LOT_PRINCIPAL = 1;
    private static final Integer UPDATED_IS_TYPE_LOT_PRINCIPAL = 2;

    private static final Boolean DEFAULT_IS_LOT_SURFACE_VARIABLE = false;
    private static final Boolean UPDATED_IS_LOT_SURFACE_VARIABLE = true;

    private static final String DEFAULT_HABITATION_OU_TERTIAIRE = "AAAAAAAAAA";
    private static final String UPDATED_HABITATION_OU_TERTIAIRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NOMBRE_PIECES = 1;
    private static final Integer UPDATED_NOMBRE_PIECES = 2;

    private static final String DEFAULT_LIBELLE_COURT = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_COURT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE = false;
    private static final Boolean UPDATED_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE = true;

    private static final Boolean DEFAULT_IS_USED_IN_KLEVALTO = false;
    private static final Boolean UPDATED_IS_USED_IN_KLEVALTO = true;

    @Autowired
    private TypeBienRepository typeBienRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeBienMockMvc;

    private TypeBien typeBien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeBienResource typeBienResource = new TypeBienResource(typeBienRepository);
        this.restTypeBienMockMvc = MockMvcBuilders.standaloneSetup(typeBienResource)
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
    public static TypeBien createEntity(EntityManager em) {
        TypeBien typeBien = new TypeBien()
            .codeTypeLot(DEFAULT_CODE_TYPE_LOT)
            .libelle(DEFAULT_LIBELLE)
            .libelleAnnonce(DEFAULT_LIBELLE_ANNONCE)
            .isHabitable(DEFAULT_IS_HABITABLE)
            .dureeVisiteLocation(DEFAULT_DUREE_VISITE_LOCATION)
            .dureeVisiteVente(DEFAULT_DUREE_VISITE_VENTE)
            .ordreAffichage(DEFAULT_ORDRE_AFFICHAGE)
            .isTypeLotPrincipal(DEFAULT_IS_TYPE_LOT_PRINCIPAL)
            .isLotSurfaceVariable(DEFAULT_IS_LOT_SURFACE_VARIABLE)
            .habitationOuTertiaire(DEFAULT_HABITATION_OU_TERTIAIRE)
            .nombrePieces(DEFAULT_NOMBRE_PIECES)
            .libelleCourt(DEFAULT_LIBELLE_COURT)
            .nbrChambreObligatoireAnnonce(DEFAULT_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE)
            .isUsedInKlevalto(DEFAULT_IS_USED_IN_KLEVALTO);
        return typeBien;
    }

    @Before
    public void initTest() {
        typeBien = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeBien() throws Exception {
        int databaseSizeBeforeCreate = typeBienRepository.findAll().size();

        // Create the TypeBien
        restTypeBienMockMvc.perform(post("/api/type-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeBien)))
            .andExpect(status().isCreated());

        // Validate the TypeBien in the database
        List<TypeBien> typeBienList = typeBienRepository.findAll();
        assertThat(typeBienList).hasSize(databaseSizeBeforeCreate + 1);
        TypeBien testTypeBien = typeBienList.get(typeBienList.size() - 1);
        assertThat(testTypeBien.getCodeTypeLot()).isEqualTo(DEFAULT_CODE_TYPE_LOT);
        assertThat(testTypeBien.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTypeBien.getLibelleAnnonce()).isEqualTo(DEFAULT_LIBELLE_ANNONCE);
        assertThat(testTypeBien.isIsHabitable()).isEqualTo(DEFAULT_IS_HABITABLE);
        assertThat(testTypeBien.getDureeVisiteLocation()).isEqualTo(DEFAULT_DUREE_VISITE_LOCATION);
        assertThat(testTypeBien.getDureeVisiteVente()).isEqualTo(DEFAULT_DUREE_VISITE_VENTE);
        assertThat(testTypeBien.getOrdreAffichage()).isEqualTo(DEFAULT_ORDRE_AFFICHAGE);
        assertThat(testTypeBien.getIsTypeLotPrincipal()).isEqualTo(DEFAULT_IS_TYPE_LOT_PRINCIPAL);
        assertThat(testTypeBien.isIsLotSurfaceVariable()).isEqualTo(DEFAULT_IS_LOT_SURFACE_VARIABLE);
        assertThat(testTypeBien.getHabitationOuTertiaire()).isEqualTo(DEFAULT_HABITATION_OU_TERTIAIRE);
        assertThat(testTypeBien.getNombrePieces()).isEqualTo(DEFAULT_NOMBRE_PIECES);
        assertThat(testTypeBien.getLibelleCourt()).isEqualTo(DEFAULT_LIBELLE_COURT);
        assertThat(testTypeBien.isNbrChambreObligatoireAnnonce()).isEqualTo(DEFAULT_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE);
        assertThat(testTypeBien.isIsUsedInKlevalto()).isEqualTo(DEFAULT_IS_USED_IN_KLEVALTO);
    }

    @Test
    @Transactional
    public void createTypeBienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeBienRepository.findAll().size();

        // Create the TypeBien with an existing ID
        typeBien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeBienMockMvc.perform(post("/api/type-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeBien)))
            .andExpect(status().isBadRequest());

        // Validate the TypeBien in the database
        List<TypeBien> typeBienList = typeBienRepository.findAll();
        assertThat(typeBienList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTypeBiens() throws Exception {
        // Initialize the database
        typeBienRepository.saveAndFlush(typeBien);

        // Get all the typeBienList
        restTypeBienMockMvc.perform(get("/api/type-biens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeBien.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeTypeLot").value(hasItem(DEFAULT_CODE_TYPE_LOT.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].libelleAnnonce").value(hasItem(DEFAULT_LIBELLE_ANNONCE.toString())))
            .andExpect(jsonPath("$.[*].isHabitable").value(hasItem(DEFAULT_IS_HABITABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].dureeVisiteLocation").value(hasItem(DEFAULT_DUREE_VISITE_LOCATION)))
            .andExpect(jsonPath("$.[*].dureeVisiteVente").value(hasItem(DEFAULT_DUREE_VISITE_VENTE)))
            .andExpect(jsonPath("$.[*].ordreAffichage").value(hasItem(DEFAULT_ORDRE_AFFICHAGE)))
            .andExpect(jsonPath("$.[*].isTypeLotPrincipal").value(hasItem(DEFAULT_IS_TYPE_LOT_PRINCIPAL)))
            .andExpect(jsonPath("$.[*].isLotSurfaceVariable").value(hasItem(DEFAULT_IS_LOT_SURFACE_VARIABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].habitationOuTertiaire").value(hasItem(DEFAULT_HABITATION_OU_TERTIAIRE.toString())))
            .andExpect(jsonPath("$.[*].nombrePieces").value(hasItem(DEFAULT_NOMBRE_PIECES)))
            .andExpect(jsonPath("$.[*].libelleCourt").value(hasItem(DEFAULT_LIBELLE_COURT.toString())))
            .andExpect(jsonPath("$.[*].nbrChambreObligatoireAnnonce").value(hasItem(DEFAULT_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE.booleanValue())))
            .andExpect(jsonPath("$.[*].isUsedInKlevalto").value(hasItem(DEFAULT_IS_USED_IN_KLEVALTO.booleanValue())));
    }

    @Test
    @Transactional
    public void getTypeBien() throws Exception {
        // Initialize the database
        typeBienRepository.saveAndFlush(typeBien);

        // Get the typeBien
        restTypeBienMockMvc.perform(get("/api/type-biens/{id}", typeBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeBien.getId().intValue()))
            .andExpect(jsonPath("$.codeTypeLot").value(DEFAULT_CODE_TYPE_LOT.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.libelleAnnonce").value(DEFAULT_LIBELLE_ANNONCE.toString()))
            .andExpect(jsonPath("$.isHabitable").value(DEFAULT_IS_HABITABLE.booleanValue()))
            .andExpect(jsonPath("$.dureeVisiteLocation").value(DEFAULT_DUREE_VISITE_LOCATION))
            .andExpect(jsonPath("$.dureeVisiteVente").value(DEFAULT_DUREE_VISITE_VENTE))
            .andExpect(jsonPath("$.ordreAffichage").value(DEFAULT_ORDRE_AFFICHAGE))
            .andExpect(jsonPath("$.isTypeLotPrincipal").value(DEFAULT_IS_TYPE_LOT_PRINCIPAL))
            .andExpect(jsonPath("$.isLotSurfaceVariable").value(DEFAULT_IS_LOT_SURFACE_VARIABLE.booleanValue()))
            .andExpect(jsonPath("$.habitationOuTertiaire").value(DEFAULT_HABITATION_OU_TERTIAIRE.toString()))
            .andExpect(jsonPath("$.nombrePieces").value(DEFAULT_NOMBRE_PIECES))
            .andExpect(jsonPath("$.libelleCourt").value(DEFAULT_LIBELLE_COURT.toString()))
            .andExpect(jsonPath("$.nbrChambreObligatoireAnnonce").value(DEFAULT_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE.booleanValue()))
            .andExpect(jsonPath("$.isUsedInKlevalto").value(DEFAULT_IS_USED_IN_KLEVALTO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeBien() throws Exception {
        // Get the typeBien
        restTypeBienMockMvc.perform(get("/api/type-biens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeBien() throws Exception {
        // Initialize the database
        typeBienRepository.saveAndFlush(typeBien);
        int databaseSizeBeforeUpdate = typeBienRepository.findAll().size();

        // Update the typeBien
        TypeBien updatedTypeBien = typeBienRepository.findOne(typeBien.getId());
        updatedTypeBien
            .codeTypeLot(UPDATED_CODE_TYPE_LOT)
            .libelle(UPDATED_LIBELLE)
            .libelleAnnonce(UPDATED_LIBELLE_ANNONCE)
            .isHabitable(UPDATED_IS_HABITABLE)
            .dureeVisiteLocation(UPDATED_DUREE_VISITE_LOCATION)
            .dureeVisiteVente(UPDATED_DUREE_VISITE_VENTE)
            .ordreAffichage(UPDATED_ORDRE_AFFICHAGE)
            .isTypeLotPrincipal(UPDATED_IS_TYPE_LOT_PRINCIPAL)
            .isLotSurfaceVariable(UPDATED_IS_LOT_SURFACE_VARIABLE)
            .habitationOuTertiaire(UPDATED_HABITATION_OU_TERTIAIRE)
            .nombrePieces(UPDATED_NOMBRE_PIECES)
            .libelleCourt(UPDATED_LIBELLE_COURT)
            .nbrChambreObligatoireAnnonce(UPDATED_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE)
            .isUsedInKlevalto(UPDATED_IS_USED_IN_KLEVALTO);

        restTypeBienMockMvc.perform(put("/api/type-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeBien)))
            .andExpect(status().isOk());

        // Validate the TypeBien in the database
        List<TypeBien> typeBienList = typeBienRepository.findAll();
        assertThat(typeBienList).hasSize(databaseSizeBeforeUpdate);
        TypeBien testTypeBien = typeBienList.get(typeBienList.size() - 1);
        assertThat(testTypeBien.getCodeTypeLot()).isEqualTo(UPDATED_CODE_TYPE_LOT);
        assertThat(testTypeBien.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTypeBien.getLibelleAnnonce()).isEqualTo(UPDATED_LIBELLE_ANNONCE);
        assertThat(testTypeBien.isIsHabitable()).isEqualTo(UPDATED_IS_HABITABLE);
        assertThat(testTypeBien.getDureeVisiteLocation()).isEqualTo(UPDATED_DUREE_VISITE_LOCATION);
        assertThat(testTypeBien.getDureeVisiteVente()).isEqualTo(UPDATED_DUREE_VISITE_VENTE);
        assertThat(testTypeBien.getOrdreAffichage()).isEqualTo(UPDATED_ORDRE_AFFICHAGE);
        assertThat(testTypeBien.getIsTypeLotPrincipal()).isEqualTo(UPDATED_IS_TYPE_LOT_PRINCIPAL);
        assertThat(testTypeBien.isIsLotSurfaceVariable()).isEqualTo(UPDATED_IS_LOT_SURFACE_VARIABLE);
        assertThat(testTypeBien.getHabitationOuTertiaire()).isEqualTo(UPDATED_HABITATION_OU_TERTIAIRE);
        assertThat(testTypeBien.getNombrePieces()).isEqualTo(UPDATED_NOMBRE_PIECES);
        assertThat(testTypeBien.getLibelleCourt()).isEqualTo(UPDATED_LIBELLE_COURT);
        assertThat(testTypeBien.isNbrChambreObligatoireAnnonce()).isEqualTo(UPDATED_NBR_CHAMBRE_OBLIGATOIRE_ANNONCE);
        assertThat(testTypeBien.isIsUsedInKlevalto()).isEqualTo(UPDATED_IS_USED_IN_KLEVALTO);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeBien() throws Exception {
        int databaseSizeBeforeUpdate = typeBienRepository.findAll().size();

        // Create the TypeBien

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTypeBienMockMvc.perform(put("/api/type-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeBien)))
            .andExpect(status().isCreated());

        // Validate the TypeBien in the database
        List<TypeBien> typeBienList = typeBienRepository.findAll();
        assertThat(typeBienList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTypeBien() throws Exception {
        // Initialize the database
        typeBienRepository.saveAndFlush(typeBien);
        int databaseSizeBeforeDelete = typeBienRepository.findAll().size();

        // Get the typeBien
        restTypeBienMockMvc.perform(delete("/api/type-biens/{id}", typeBien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeBien> typeBienList = typeBienRepository.findAll();
        assertThat(typeBienList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeBien.class);
        TypeBien typeBien1 = new TypeBien();
        typeBien1.setId(1L);
        TypeBien typeBien2 = new TypeBien();
        typeBien2.setId(typeBien1.getId());
        assertThat(typeBien1).isEqualTo(typeBien2);
        typeBien2.setId(2L);
        assertThat(typeBien1).isNotEqualTo(typeBien2);
        typeBien1.setId(null);
        assertThat(typeBien1).isNotEqualTo(typeBien2);
    }
}
