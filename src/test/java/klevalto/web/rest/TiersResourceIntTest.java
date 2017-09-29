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
import klevalto.domain.Tiers;
import klevalto.domain.enumeration.Civilite;
import klevalto.domain.enumeration.RegimeMatrimonial;
import klevalto.repository.TiersRepository;
import klevalto.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the TiersResource REST controller.
 *
 * @see TiersResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class TiersResourceIntTest {

    private static final String DEFAULT_ADRESSE_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_MAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NO_MAIL = false;
    private static final Boolean UPDATED_NO_MAIL = true;

    private static final String DEFAULT_MOT_DE_PASSE = "AAAAAAAAAA";
    private static final String UPDATED_MOT_DE_PASSE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_COMPTE_TIERS = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_COMPTE_TIERS = "BBBBBBBBBB";

    private static final Civilite DEFAULT_CIVILITE = Civilite.M;
    private static final Civilite UPDATED_CIVILITE = Civilite.MME;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_FIXE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_FIXE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PROSPECT_LOCATAIRE = false;
    private static final Boolean UPDATED_IS_PROSPECT_LOCATAIRE = true;

    private static final Boolean DEFAULT_IS_LOCATAIRE = false;
    private static final Boolean UPDATED_IS_LOCATAIRE = true;

    private static final Boolean DEFAULT_IS_BAILLEUR = false;
    private static final Boolean UPDATED_IS_BAILLEUR = true;

    private static final Boolean DEFAULT_IS_BAILLEUR_RECHERCHE = false;
    private static final Boolean UPDATED_IS_BAILLEUR_RECHERCHE = true;

    private static final Boolean DEFAULT_IS_PERSONNEL_SERGIC = false;
    private static final Boolean UPDATED_IS_PERSONNEL_SERGIC = true;

    private static final Boolean DEFAULT_IS_ADMINISTRATEUR = false;
    private static final Boolean UPDATED_IS_ADMINISTRATEUR = true;

    private static final Boolean DEFAULT_IS_ACQUEREUR = false;
    private static final Boolean UPDATED_IS_ACQUEREUR = true;

    private static final Boolean DEFAULT_IS_COPROPRIETAIRE = false;
    private static final Boolean UPDATED_IS_COPROPRIETAIRE = true;

    private static final Boolean DEFAULT_IS_PROPRIETAIRE = false;
    private static final Boolean UPDATED_IS_PROPRIETAIRE = true;

    private static final Boolean DEFAULT_IS_AUTRE_ACTEUR = false;
    private static final Boolean UPDATED_IS_AUTRE_ACTEUR = true;

    private static final Boolean DEFAULT_IS_PROPRIETAIRE_LOCATION_SAISONNIERE = false;
    private static final Boolean UPDATED_IS_PROPRIETAIRE_LOCATION_SAISONNIERE = true;

    private static final Boolean DEFAULT_IS_INTERVENANT = false;
    private static final Boolean UPDATED_IS_INTERVENANT = true;

    private static final RegimeMatrimonial DEFAULT_REGIME_MATRIMONIAL = RegimeMatrimonial.PACSE;
    private static final RegimeMatrimonial UPDATED_REGIME_MATRIMONIAL = RegimeMatrimonial.SEPARE;

    private static final String DEFAULT_EMPLOYEUR = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEUR = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_EMBAUCHE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_EMBAUCHE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Float DEFAULT_REVENU_MENSUEL = 1F;
    private static final Float UPDATED_REVENU_MENSUEL = 2F;

    private static final Integer DEFAULT_NOMBRE_MOIS_DE_REVENU = 1;
    private static final Integer UPDATED_NOMBRE_MOIS_DE_REVENU = 2;

    private static final ZonedDateTime DEFAULT_DATE_DE_NAISSANCE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DE_NAISSANCE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LIEU_DE_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_DE_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SEPARATED = false;
    private static final Boolean UPDATED_IS_SEPARATED = true;

    private static final ZonedDateTime DEFAULT_DATE_DE_MARIAGE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DE_MARIAGE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LIEU_DE_MARIAGE = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_DE_MARIAGE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTAIRES = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SMS_ACCEPTED = false;
    private static final Boolean UPDATED_SMS_ACCEPTED = true;

    private static final Boolean DEFAULT_ADRESSE_POSTALE_NPAI = false;
    private static final Boolean UPDATED_ADRESSE_POSTALE_NPAI = true;

    @Autowired
    private TiersRepository tiersRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTiersMockMvc;

    private Tiers tiers;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TiersResource tiersResource = new TiersResource(tiersRepository);
        this.restTiersMockMvc = MockMvcBuilders.standaloneSetup(tiersResource)
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
    public static Tiers createEntity(EntityManager em) {
        Tiers tiers = new Tiers()
            .adresseMail(DEFAULT_ADRESSE_MAIL)
            .noMail(DEFAULT_NO_MAIL)
            .motDePasse(DEFAULT_MOT_DE_PASSE)
            .identifiantCompteTiers(DEFAULT_IDENTIFIANT_COMPTE_TIERS)
            .civilite(DEFAULT_CIVILITE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .telephoneMobile(DEFAULT_TELEPHONE_MOBILE)
            .telephoneFixe(DEFAULT_TELEPHONE_FIXE)
            .isProspectLocataire(DEFAULT_IS_PROSPECT_LOCATAIRE)
            .isLocataire(DEFAULT_IS_LOCATAIRE)
            .isBailleur(DEFAULT_IS_BAILLEUR)
            .isBailleurRecherche(DEFAULT_IS_BAILLEUR_RECHERCHE)
            .isPersonnelSergic(DEFAULT_IS_PERSONNEL_SERGIC)
            .isAdministrateur(DEFAULT_IS_ADMINISTRATEUR)
            .isAcquereur(DEFAULT_IS_ACQUEREUR)
            .isCoproprietaire(DEFAULT_IS_COPROPRIETAIRE)
            .isProprietaire(DEFAULT_IS_PROPRIETAIRE)
            .isAutreActeur(DEFAULT_IS_AUTRE_ACTEUR)
            .isProprietaireLocationSaisonniere(DEFAULT_IS_PROPRIETAIRE_LOCATION_SAISONNIERE)
            .isIntervenant(DEFAULT_IS_INTERVENANT)
            .regimeMatrimonial(DEFAULT_REGIME_MATRIMONIAL)
            .employeur(DEFAULT_EMPLOYEUR)
            .profession(DEFAULT_PROFESSION)
            .dateEmbauche(DEFAULT_DATE_EMBAUCHE)
            .revenuMensuel(DEFAULT_REVENU_MENSUEL)
            .nombreMoisDeRevenu(DEFAULT_NOMBRE_MOIS_DE_REVENU)
            .dateDeNaissance(DEFAULT_DATE_DE_NAISSANCE)
            .lieuDeNaissance(DEFAULT_LIEU_DE_NAISSANCE)
            .nationalite(DEFAULT_NATIONALITE)
            .isSeparated(DEFAULT_IS_SEPARATED)
            .dateDeMariage(DEFAULT_DATE_DE_MARIAGE)
            .lieuDeMariage(DEFAULT_LIEU_DE_MARIAGE)
            .commentaires(DEFAULT_COMMENTAIRES)
            .smsAccepted(DEFAULT_SMS_ACCEPTED)
            .adressePostaleNPAI(DEFAULT_ADRESSE_POSTALE_NPAI);
        return tiers;
    }

    @Before
    public void initTest() {
        tiers = createEntity(em);
    }

    @Test
    @Transactional
    public void createTiers() throws Exception {
        int databaseSizeBeforeCreate = tiersRepository.findAll().size();

        // Create the Tiers
        restTiersMockMvc.perform(post("/api/tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tiers)))
            .andExpect(status().isCreated());

        // Validate the Tiers in the database
        List<Tiers> tiersList = tiersRepository.findAll();
        assertThat(tiersList).hasSize(databaseSizeBeforeCreate + 1);
        Tiers testTiers = tiersList.get(tiersList.size() - 1);
        assertThat(testTiers.getAdresseMail()).isEqualTo(DEFAULT_ADRESSE_MAIL);
        assertThat(testTiers.isNoMail()).isEqualTo(DEFAULT_NO_MAIL);
        assertThat(testTiers.getMotDePasse()).isEqualTo(DEFAULT_MOT_DE_PASSE);
        assertThat(testTiers.getIdentifiantCompteTiers()).isEqualTo(DEFAULT_IDENTIFIANT_COMPTE_TIERS);
        assertThat(testTiers.getCivilite()).isEqualTo(DEFAULT_CIVILITE);
        assertThat(testTiers.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTiers.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testTiers.getTelephoneMobile()).isEqualTo(DEFAULT_TELEPHONE_MOBILE);
        assertThat(testTiers.getTelephoneFixe()).isEqualTo(DEFAULT_TELEPHONE_FIXE);
        assertThat(testTiers.isIsProspectLocataire()).isEqualTo(DEFAULT_IS_PROSPECT_LOCATAIRE);
        assertThat(testTiers.isIsLocataire()).isEqualTo(DEFAULT_IS_LOCATAIRE);
        assertThat(testTiers.isIsBailleur()).isEqualTo(DEFAULT_IS_BAILLEUR);
        assertThat(testTiers.isIsBailleurRecherche()).isEqualTo(DEFAULT_IS_BAILLEUR_RECHERCHE);
        assertThat(testTiers.isIsPersonnelSergic()).isEqualTo(DEFAULT_IS_PERSONNEL_SERGIC);
        assertThat(testTiers.isIsAdministrateur()).isEqualTo(DEFAULT_IS_ADMINISTRATEUR);
        assertThat(testTiers.isIsAcquereur()).isEqualTo(DEFAULT_IS_ACQUEREUR);
        assertThat(testTiers.isIsCoproprietaire()).isEqualTo(DEFAULT_IS_COPROPRIETAIRE);
        assertThat(testTiers.isIsProprietaire()).isEqualTo(DEFAULT_IS_PROPRIETAIRE);
        assertThat(testTiers.isIsAutreActeur()).isEqualTo(DEFAULT_IS_AUTRE_ACTEUR);
        assertThat(testTiers.isIsProprietaireLocationSaisonniere()).isEqualTo(DEFAULT_IS_PROPRIETAIRE_LOCATION_SAISONNIERE);
        assertThat(testTiers.isIsIntervenant()).isEqualTo(DEFAULT_IS_INTERVENANT);
        assertThat(testTiers.getRegimeMatrimonial()).isEqualTo(DEFAULT_REGIME_MATRIMONIAL);
        assertThat(testTiers.getEmployeur()).isEqualTo(DEFAULT_EMPLOYEUR);
        assertThat(testTiers.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testTiers.getDateEmbauche()).isEqualTo(DEFAULT_DATE_EMBAUCHE);
        assertThat(testTiers.getRevenuMensuel()).isEqualTo(DEFAULT_REVENU_MENSUEL);
        assertThat(testTiers.getNombreMoisDeRevenu()).isEqualTo(DEFAULT_NOMBRE_MOIS_DE_REVENU);
        assertThat(testTiers.getDateDeNaissance()).isEqualTo(DEFAULT_DATE_DE_NAISSANCE);
        assertThat(testTiers.getLieuDeNaissance()).isEqualTo(DEFAULT_LIEU_DE_NAISSANCE);
        assertThat(testTiers.getNationalite()).isEqualTo(DEFAULT_NATIONALITE);
        assertThat(testTiers.isIsSeparated()).isEqualTo(DEFAULT_IS_SEPARATED);
        assertThat(testTiers.getDateDeMariage()).isEqualTo(DEFAULT_DATE_DE_MARIAGE);
        assertThat(testTiers.getLieuDeMariage()).isEqualTo(DEFAULT_LIEU_DE_MARIAGE);
        assertThat(testTiers.getCommentaires()).isEqualTo(DEFAULT_COMMENTAIRES);
        assertThat(testTiers.isSmsAccepted()).isEqualTo(DEFAULT_SMS_ACCEPTED);
        assertThat(testTiers.isAdressePostaleNPAI()).isEqualTo(DEFAULT_ADRESSE_POSTALE_NPAI);
    }

    @Test
    @Transactional
    public void createTiersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tiersRepository.findAll().size();

        // Create the Tiers with an existing ID
        tiers.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTiersMockMvc.perform(post("/api/tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tiers)))
            .andExpect(status().isBadRequest());

        // Validate the Tiers in the database
        List<Tiers> tiersList = tiersRepository.findAll();
        assertThat(tiersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTiers() throws Exception {
        // Initialize the database
        tiersRepository.saveAndFlush(tiers);

        // Get all the tiersList
        restTiersMockMvc.perform(get("/api/tiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tiers.getId().intValue())))
            .andExpect(jsonPath("$.[*].adresseMail").value(hasItem(DEFAULT_ADRESSE_MAIL.toString())))
            .andExpect(jsonPath("$.[*].noMail").value(hasItem(DEFAULT_NO_MAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].motDePasse").value(hasItem(DEFAULT_MOT_DE_PASSE.toString())))
            .andExpect(jsonPath("$.[*].identifiantCompteTiers").value(hasItem(DEFAULT_IDENTIFIANT_COMPTE_TIERS.toString())))
            .andExpect(jsonPath("$.[*].civilite").value(hasItem(DEFAULT_CIVILITE.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].telephoneMobile").value(hasItem(DEFAULT_TELEPHONE_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].telephoneFixe").value(hasItem(DEFAULT_TELEPHONE_FIXE.toString())))
            .andExpect(jsonPath("$.[*].isProspectLocataire").value(hasItem(DEFAULT_IS_PROSPECT_LOCATAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].isLocataire").value(hasItem(DEFAULT_IS_LOCATAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].isBailleur").value(hasItem(DEFAULT_IS_BAILLEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].isBailleurRecherche").value(hasItem(DEFAULT_IS_BAILLEUR_RECHERCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].isPersonnelSergic").value(hasItem(DEFAULT_IS_PERSONNEL_SERGIC.booleanValue())))
            .andExpect(jsonPath("$.[*].isAdministrateur").value(hasItem(DEFAULT_IS_ADMINISTRATEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].isAcquereur").value(hasItem(DEFAULT_IS_ACQUEREUR.booleanValue())))
            .andExpect(jsonPath("$.[*].isCoproprietaire").value(hasItem(DEFAULT_IS_COPROPRIETAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].isProprietaire").value(hasItem(DEFAULT_IS_PROPRIETAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].isAutreActeur").value(hasItem(DEFAULT_IS_AUTRE_ACTEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].isProprietaireLocationSaisonniere").value(hasItem(DEFAULT_IS_PROPRIETAIRE_LOCATION_SAISONNIERE.booleanValue())))
            .andExpect(jsonPath("$.[*].isIntervenant").value(hasItem(DEFAULT_IS_INTERVENANT.booleanValue())))
            .andExpect(jsonPath("$.[*].regimeMatrimonial").value(hasItem(DEFAULT_REGIME_MATRIMONIAL.toString())))
            .andExpect(jsonPath("$.[*].employeur").value(hasItem(DEFAULT_EMPLOYEUR.toString())))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION.toString())))
            .andExpect(jsonPath("$.[*].dateEmbauche").value(hasItem(sameInstant(DEFAULT_DATE_EMBAUCHE))))
            .andExpect(jsonPath("$.[*].revenuMensuel").value(hasItem(DEFAULT_REVENU_MENSUEL.doubleValue())))
            .andExpect(jsonPath("$.[*].nombreMoisDeRevenu").value(hasItem(DEFAULT_NOMBRE_MOIS_DE_REVENU)))
            .andExpect(jsonPath("$.[*].dateDeNaissance").value(hasItem(sameInstant(DEFAULT_DATE_DE_NAISSANCE))))
            .andExpect(jsonPath("$.[*].lieuDeNaissance").value(hasItem(DEFAULT_LIEU_DE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].nationalite").value(hasItem(DEFAULT_NATIONALITE.toString())))
            .andExpect(jsonPath("$.[*].isSeparated").value(hasItem(DEFAULT_IS_SEPARATED.booleanValue())))
            .andExpect(jsonPath("$.[*].dateDeMariage").value(hasItem(sameInstant(DEFAULT_DATE_DE_MARIAGE))))
            .andExpect(jsonPath("$.[*].lieuDeMariage").value(hasItem(DEFAULT_LIEU_DE_MARIAGE.toString())))
            .andExpect(jsonPath("$.[*].commentaires").value(hasItem(DEFAULT_COMMENTAIRES.toString())))
            .andExpect(jsonPath("$.[*].smsAccepted").value(hasItem(DEFAULT_SMS_ACCEPTED.booleanValue())))
            .andExpect(jsonPath("$.[*].adressePostaleNPAI").value(hasItem(DEFAULT_ADRESSE_POSTALE_NPAI.booleanValue())));
    }

    @Test
    @Transactional
    public void getTiers() throws Exception {
        // Initialize the database
        tiersRepository.saveAndFlush(tiers);

        // Get the tiers
        restTiersMockMvc.perform(get("/api/tiers/{id}", tiers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tiers.getId().intValue()))
            .andExpect(jsonPath("$.adresseMail").value(DEFAULT_ADRESSE_MAIL.toString()))
            .andExpect(jsonPath("$.noMail").value(DEFAULT_NO_MAIL.booleanValue()))
            .andExpect(jsonPath("$.motDePasse").value(DEFAULT_MOT_DE_PASSE.toString()))
            .andExpect(jsonPath("$.identifiantCompteTiers").value(DEFAULT_IDENTIFIANT_COMPTE_TIERS.toString()))
            .andExpect(jsonPath("$.civilite").value(DEFAULT_CIVILITE.toString()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.telephoneMobile").value(DEFAULT_TELEPHONE_MOBILE.toString()))
            .andExpect(jsonPath("$.telephoneFixe").value(DEFAULT_TELEPHONE_FIXE.toString()))
            .andExpect(jsonPath("$.isProspectLocataire").value(DEFAULT_IS_PROSPECT_LOCATAIRE.booleanValue()))
            .andExpect(jsonPath("$.isLocataire").value(DEFAULT_IS_LOCATAIRE.booleanValue()))
            .andExpect(jsonPath("$.isBailleur").value(DEFAULT_IS_BAILLEUR.booleanValue()))
            .andExpect(jsonPath("$.isBailleurRecherche").value(DEFAULT_IS_BAILLEUR_RECHERCHE.booleanValue()))
            .andExpect(jsonPath("$.isPersonnelSergic").value(DEFAULT_IS_PERSONNEL_SERGIC.booleanValue()))
            .andExpect(jsonPath("$.isAdministrateur").value(DEFAULT_IS_ADMINISTRATEUR.booleanValue()))
            .andExpect(jsonPath("$.isAcquereur").value(DEFAULT_IS_ACQUEREUR.booleanValue()))
            .andExpect(jsonPath("$.isCoproprietaire").value(DEFAULT_IS_COPROPRIETAIRE.booleanValue()))
            .andExpect(jsonPath("$.isProprietaire").value(DEFAULT_IS_PROPRIETAIRE.booleanValue()))
            .andExpect(jsonPath("$.isAutreActeur").value(DEFAULT_IS_AUTRE_ACTEUR.booleanValue()))
            .andExpect(jsonPath("$.isProprietaireLocationSaisonniere").value(DEFAULT_IS_PROPRIETAIRE_LOCATION_SAISONNIERE.booleanValue()))
            .andExpect(jsonPath("$.isIntervenant").value(DEFAULT_IS_INTERVENANT.booleanValue()))
            .andExpect(jsonPath("$.regimeMatrimonial").value(DEFAULT_REGIME_MATRIMONIAL.toString()))
            .andExpect(jsonPath("$.employeur").value(DEFAULT_EMPLOYEUR.toString()))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION.toString()))
            .andExpect(jsonPath("$.dateEmbauche").value(sameInstant(DEFAULT_DATE_EMBAUCHE)))
            .andExpect(jsonPath("$.revenuMensuel").value(DEFAULT_REVENU_MENSUEL.doubleValue()))
            .andExpect(jsonPath("$.nombreMoisDeRevenu").value(DEFAULT_NOMBRE_MOIS_DE_REVENU))
            .andExpect(jsonPath("$.dateDeNaissance").value(sameInstant(DEFAULT_DATE_DE_NAISSANCE)))
            .andExpect(jsonPath("$.lieuDeNaissance").value(DEFAULT_LIEU_DE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.nationalite").value(DEFAULT_NATIONALITE.toString()))
            .andExpect(jsonPath("$.isSeparated").value(DEFAULT_IS_SEPARATED.booleanValue()))
            .andExpect(jsonPath("$.dateDeMariage").value(sameInstant(DEFAULT_DATE_DE_MARIAGE)))
            .andExpect(jsonPath("$.lieuDeMariage").value(DEFAULT_LIEU_DE_MARIAGE.toString()))
            .andExpect(jsonPath("$.commentaires").value(DEFAULT_COMMENTAIRES.toString()))
            .andExpect(jsonPath("$.smsAccepted").value(DEFAULT_SMS_ACCEPTED.booleanValue()))
            .andExpect(jsonPath("$.adressePostaleNPAI").value(DEFAULT_ADRESSE_POSTALE_NPAI.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTiers() throws Exception {
        // Get the tiers
        restTiersMockMvc.perform(get("/api/tiers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTiers() throws Exception {
        // Initialize the database
        tiersRepository.saveAndFlush(tiers);
        int databaseSizeBeforeUpdate = tiersRepository.findAll().size();

        // Update the tiers
        Tiers updatedTiers = tiersRepository.findOne(tiers.getId());
        updatedTiers
            .adresseMail(UPDATED_ADRESSE_MAIL)
            .noMail(UPDATED_NO_MAIL)
            .motDePasse(UPDATED_MOT_DE_PASSE)
            .identifiantCompteTiers(UPDATED_IDENTIFIANT_COMPTE_TIERS)
            .civilite(UPDATED_CIVILITE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .telephoneMobile(UPDATED_TELEPHONE_MOBILE)
            .telephoneFixe(UPDATED_TELEPHONE_FIXE)
            .isProspectLocataire(UPDATED_IS_PROSPECT_LOCATAIRE)
            .isLocataire(UPDATED_IS_LOCATAIRE)
            .isBailleur(UPDATED_IS_BAILLEUR)
            .isBailleurRecherche(UPDATED_IS_BAILLEUR_RECHERCHE)
            .isPersonnelSergic(UPDATED_IS_PERSONNEL_SERGIC)
            .isAdministrateur(UPDATED_IS_ADMINISTRATEUR)
            .isAcquereur(UPDATED_IS_ACQUEREUR)
            .isCoproprietaire(UPDATED_IS_COPROPRIETAIRE)
            .isProprietaire(UPDATED_IS_PROPRIETAIRE)
            .isAutreActeur(UPDATED_IS_AUTRE_ACTEUR)
            .isProprietaireLocationSaisonniere(UPDATED_IS_PROPRIETAIRE_LOCATION_SAISONNIERE)
            .isIntervenant(UPDATED_IS_INTERVENANT)
            .regimeMatrimonial(UPDATED_REGIME_MATRIMONIAL)
            .employeur(UPDATED_EMPLOYEUR)
            .profession(UPDATED_PROFESSION)
            .dateEmbauche(UPDATED_DATE_EMBAUCHE)
            .revenuMensuel(UPDATED_REVENU_MENSUEL)
            .nombreMoisDeRevenu(UPDATED_NOMBRE_MOIS_DE_REVENU)
            .dateDeNaissance(UPDATED_DATE_DE_NAISSANCE)
            .lieuDeNaissance(UPDATED_LIEU_DE_NAISSANCE)
            .nationalite(UPDATED_NATIONALITE)
            .isSeparated(UPDATED_IS_SEPARATED)
            .dateDeMariage(UPDATED_DATE_DE_MARIAGE)
            .lieuDeMariage(UPDATED_LIEU_DE_MARIAGE)
            .commentaires(UPDATED_COMMENTAIRES)
            .smsAccepted(UPDATED_SMS_ACCEPTED)
            .adressePostaleNPAI(UPDATED_ADRESSE_POSTALE_NPAI);

        restTiersMockMvc.perform(put("/api/tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTiers)))
            .andExpect(status().isOk());

        // Validate the Tiers in the database
        List<Tiers> tiersList = tiersRepository.findAll();
        assertThat(tiersList).hasSize(databaseSizeBeforeUpdate);
        Tiers testTiers = tiersList.get(tiersList.size() - 1);
        assertThat(testTiers.getAdresseMail()).isEqualTo(UPDATED_ADRESSE_MAIL);
        assertThat(testTiers.isNoMail()).isEqualTo(UPDATED_NO_MAIL);
        assertThat(testTiers.getMotDePasse()).isEqualTo(UPDATED_MOT_DE_PASSE);
        assertThat(testTiers.getIdentifiantCompteTiers()).isEqualTo(UPDATED_IDENTIFIANT_COMPTE_TIERS);
        assertThat(testTiers.getCivilite()).isEqualTo(UPDATED_CIVILITE);
        assertThat(testTiers.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTiers.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testTiers.getTelephoneMobile()).isEqualTo(UPDATED_TELEPHONE_MOBILE);
        assertThat(testTiers.getTelephoneFixe()).isEqualTo(UPDATED_TELEPHONE_FIXE);
        assertThat(testTiers.isIsProspectLocataire()).isEqualTo(UPDATED_IS_PROSPECT_LOCATAIRE);
        assertThat(testTiers.isIsLocataire()).isEqualTo(UPDATED_IS_LOCATAIRE);
        assertThat(testTiers.isIsBailleur()).isEqualTo(UPDATED_IS_BAILLEUR);
        assertThat(testTiers.isIsBailleurRecherche()).isEqualTo(UPDATED_IS_BAILLEUR_RECHERCHE);
        assertThat(testTiers.isIsPersonnelSergic()).isEqualTo(UPDATED_IS_PERSONNEL_SERGIC);
        assertThat(testTiers.isIsAdministrateur()).isEqualTo(UPDATED_IS_ADMINISTRATEUR);
        assertThat(testTiers.isIsAcquereur()).isEqualTo(UPDATED_IS_ACQUEREUR);
        assertThat(testTiers.isIsCoproprietaire()).isEqualTo(UPDATED_IS_COPROPRIETAIRE);
        assertThat(testTiers.isIsProprietaire()).isEqualTo(UPDATED_IS_PROPRIETAIRE);
        assertThat(testTiers.isIsAutreActeur()).isEqualTo(UPDATED_IS_AUTRE_ACTEUR);
        assertThat(testTiers.isIsProprietaireLocationSaisonniere()).isEqualTo(UPDATED_IS_PROPRIETAIRE_LOCATION_SAISONNIERE);
        assertThat(testTiers.isIsIntervenant()).isEqualTo(UPDATED_IS_INTERVENANT);
        assertThat(testTiers.getRegimeMatrimonial()).isEqualTo(UPDATED_REGIME_MATRIMONIAL);
        assertThat(testTiers.getEmployeur()).isEqualTo(UPDATED_EMPLOYEUR);
        assertThat(testTiers.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testTiers.getDateEmbauche()).isEqualTo(UPDATED_DATE_EMBAUCHE);
        assertThat(testTiers.getRevenuMensuel()).isEqualTo(UPDATED_REVENU_MENSUEL);
        assertThat(testTiers.getNombreMoisDeRevenu()).isEqualTo(UPDATED_NOMBRE_MOIS_DE_REVENU);
        assertThat(testTiers.getDateDeNaissance()).isEqualTo(UPDATED_DATE_DE_NAISSANCE);
        assertThat(testTiers.getLieuDeNaissance()).isEqualTo(UPDATED_LIEU_DE_NAISSANCE);
        assertThat(testTiers.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
        assertThat(testTiers.isIsSeparated()).isEqualTo(UPDATED_IS_SEPARATED);
        assertThat(testTiers.getDateDeMariage()).isEqualTo(UPDATED_DATE_DE_MARIAGE);
        assertThat(testTiers.getLieuDeMariage()).isEqualTo(UPDATED_LIEU_DE_MARIAGE);
        assertThat(testTiers.getCommentaires()).isEqualTo(UPDATED_COMMENTAIRES);
        assertThat(testTiers.isSmsAccepted()).isEqualTo(UPDATED_SMS_ACCEPTED);
        assertThat(testTiers.isAdressePostaleNPAI()).isEqualTo(UPDATED_ADRESSE_POSTALE_NPAI);
    }

    @Test
    @Transactional
    public void updateNonExistingTiers() throws Exception {
        int databaseSizeBeforeUpdate = tiersRepository.findAll().size();

        // Create the Tiers

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTiersMockMvc.perform(put("/api/tiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tiers)))
            .andExpect(status().isCreated());

        // Validate the Tiers in the database
        List<Tiers> tiersList = tiersRepository.findAll();
        assertThat(tiersList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTiers() throws Exception {
        // Initialize the database
        tiersRepository.saveAndFlush(tiers);
        int databaseSizeBeforeDelete = tiersRepository.findAll().size();

        // Get the tiers
        restTiersMockMvc.perform(delete("/api/tiers/{id}", tiers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tiers> tiersList = tiersRepository.findAll();
        assertThat(tiersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tiers.class);
        Tiers tiers1 = new Tiers();
        tiers1.setId(1L);
        Tiers tiers2 = new Tiers();
        tiers2.setId(tiers1.getId());
        assertThat(tiers1).isEqualTo(tiers2);
        tiers2.setId(2L);
        assertThat(tiers1).isNotEqualTo(tiers2);
        tiers1.setId(null);
        assertThat(tiers1).isNotEqualTo(tiers2);
    }
}
