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
import klevalto.domain.Bail;
import klevalto.repository.BailRepository;
import klevalto.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the BailResource REST controller.
 *
 * @see BailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class BailResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE_CREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_STATUT_SIGNATURE_BAILLEUR = "AAAAAAAAAA";
    private static final String UPDATED_STATUT_SIGNATURE_BAILLEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_DETRANSFERT_VERS_MAYA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DETRANSFERT_VERS_MAYA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private BailRepository bailRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBailMockMvc;

    private Bail bail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BailResource bailResource = new BailResource(bailRepository);
        this.restBailMockMvc = MockMvcBuilders.standaloneSetup(bailResource)
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
    public static Bail createEntity(EntityManager em) {
        Bail bail = new Bail()
            .dateCreation(DEFAULT_DATE_CREATION)
            .statutSignatureBailleur(DEFAULT_STATUT_SIGNATURE_BAILLEUR)
            .dateDetransfertVersMaya(DEFAULT_DATE_DETRANSFERT_VERS_MAYA);
        return bail;
    }

    @Before
    public void initTest() {
        bail = createEntity(em);
    }

    @Test
    @Transactional
    public void createBail() throws Exception {
        int databaseSizeBeforeCreate = bailRepository.findAll().size();

        // Create the Bail
        restBailMockMvc.perform(post("/api/bails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bail)))
            .andExpect(status().isCreated());

        // Validate the Bail in the database
        List<Bail> bailList = bailRepository.findAll();
        assertThat(bailList).hasSize(databaseSizeBeforeCreate + 1);
        Bail testBail = bailList.get(bailList.size() - 1);
        assertThat(testBail.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testBail.getStatutSignatureBailleur()).isEqualTo(DEFAULT_STATUT_SIGNATURE_BAILLEUR);
        assertThat(testBail.getDateDetransfertVersMaya()).isEqualTo(DEFAULT_DATE_DETRANSFERT_VERS_MAYA);
    }

    @Test
    @Transactional
    public void createBailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bailRepository.findAll().size();

        // Create the Bail with an existing ID
        bail.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBailMockMvc.perform(post("/api/bails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bail)))
            .andExpect(status().isBadRequest());

        // Validate the Bail in the database
        List<Bail> bailList = bailRepository.findAll();
        assertThat(bailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBails() throws Exception {
        // Initialize the database
        bailRepository.saveAndFlush(bail);

        // Get all the bailList
        restBailMockMvc.perform(get("/api/bails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bail.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(sameInstant(DEFAULT_DATE_CREATION))))
            .andExpect(jsonPath("$.[*].statutSignatureBailleur").value(hasItem(DEFAULT_STATUT_SIGNATURE_BAILLEUR.toString())))
            .andExpect(jsonPath("$.[*].dateDetransfertVersMaya").value(hasItem(sameInstant(DEFAULT_DATE_DETRANSFERT_VERS_MAYA))));
    }

    @Test
    @Transactional
    public void getBail() throws Exception {
        // Initialize the database
        bailRepository.saveAndFlush(bail);

        // Get the bail
        restBailMockMvc.perform(get("/api/bails/{id}", bail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bail.getId().intValue()))
            .andExpect(jsonPath("$.dateCreation").value(sameInstant(DEFAULT_DATE_CREATION)))
            .andExpect(jsonPath("$.statutSignatureBailleur").value(DEFAULT_STATUT_SIGNATURE_BAILLEUR.toString()))
            .andExpect(jsonPath("$.dateDetransfertVersMaya").value(sameInstant(DEFAULT_DATE_DETRANSFERT_VERS_MAYA)));
    }

    @Test
    @Transactional
    public void getNonExistingBail() throws Exception {
        // Get the bail
        restBailMockMvc.perform(get("/api/bails/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBail() throws Exception {
        // Initialize the database
        bailRepository.saveAndFlush(bail);
        int databaseSizeBeforeUpdate = bailRepository.findAll().size();

        // Update the bail
        Bail updatedBail = bailRepository.findOne(bail.getId());
        updatedBail
            .dateCreation(UPDATED_DATE_CREATION)
            .statutSignatureBailleur(UPDATED_STATUT_SIGNATURE_BAILLEUR)
            .dateDetransfertVersMaya(UPDATED_DATE_DETRANSFERT_VERS_MAYA);

        restBailMockMvc.perform(put("/api/bails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBail)))
            .andExpect(status().isOk());

        // Validate the Bail in the database
        List<Bail> bailList = bailRepository.findAll();
        assertThat(bailList).hasSize(databaseSizeBeforeUpdate);
        Bail testBail = bailList.get(bailList.size() - 1);
        assertThat(testBail.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testBail.getStatutSignatureBailleur()).isEqualTo(UPDATED_STATUT_SIGNATURE_BAILLEUR);
        assertThat(testBail.getDateDetransfertVersMaya()).isEqualTo(UPDATED_DATE_DETRANSFERT_VERS_MAYA);
    }

    @Test
    @Transactional
    public void updateNonExistingBail() throws Exception {
        int databaseSizeBeforeUpdate = bailRepository.findAll().size();

        // Create the Bail

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBailMockMvc.perform(put("/api/bails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bail)))
            .andExpect(status().isCreated());

        // Validate the Bail in the database
        List<Bail> bailList = bailRepository.findAll();
        assertThat(bailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBail() throws Exception {
        // Initialize the database
        bailRepository.saveAndFlush(bail);
        int databaseSizeBeforeDelete = bailRepository.findAll().size();

        // Get the bail
        restBailMockMvc.perform(delete("/api/bails/{id}", bail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bail> bailList = bailRepository.findAll();
        assertThat(bailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bail.class);
        Bail bail1 = new Bail();
        bail1.setId(1L);
        Bail bail2 = new Bail();
        bail2.setId(bail1.getId());
        assertThat(bail1).isEqualTo(bail2);
        bail2.setId(2L);
        assertThat(bail1).isNotEqualTo(bail2);
        bail1.setId(null);
        assertThat(bail1).isNotEqualTo(bail2);
    }
}
