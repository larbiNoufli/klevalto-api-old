package klevalto.web.rest;

import klevalto.SergicApp;

import klevalto.domain.LocataireBien;
import klevalto.repository.LocataireBienRepository;
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
 * Test class for the LocataireBienResource REST controller.
 *
 * @see LocataireBienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
public class LocataireBienResourceIntTest {

    @Autowired
    private LocataireBienRepository locataireBienRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLocataireBienMockMvc;

    private LocataireBien locataireBien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocataireBienResource locataireBienResource = new LocataireBienResource(locataireBienRepository);
        this.restLocataireBienMockMvc = MockMvcBuilders.standaloneSetup(locataireBienResource)
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
    public static LocataireBien createEntity(EntityManager em) {
        LocataireBien locataireBien = new LocataireBien();
        return locataireBien;
    }

    @Before
    public void initTest() {
        locataireBien = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocataireBien() throws Exception {
        int databaseSizeBeforeCreate = locataireBienRepository.findAll().size();

        // Create the LocataireBien
        restLocataireBienMockMvc.perform(post("/api/locataire-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locataireBien)))
            .andExpect(status().isCreated());

        // Validate the LocataireBien in the database
        List<LocataireBien> locataireBienList = locataireBienRepository.findAll();
        assertThat(locataireBienList).hasSize(databaseSizeBeforeCreate + 1);
        LocataireBien testLocataireBien = locataireBienList.get(locataireBienList.size() - 1);
    }

    @Test
    @Transactional
    public void createLocataireBienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locataireBienRepository.findAll().size();

        // Create the LocataireBien with an existing ID
        locataireBien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocataireBienMockMvc.perform(post("/api/locataire-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locataireBien)))
            .andExpect(status().isBadRequest());

        // Validate the LocataireBien in the database
        List<LocataireBien> locataireBienList = locataireBienRepository.findAll();
        assertThat(locataireBienList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLocataireBiens() throws Exception {
        // Initialize the database
        locataireBienRepository.saveAndFlush(locataireBien);

        // Get all the locataireBienList
        restLocataireBienMockMvc.perform(get("/api/locataire-biens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locataireBien.getId().intValue())));
    }

    @Test
    @Transactional
    public void getLocataireBien() throws Exception {
        // Initialize the database
        locataireBienRepository.saveAndFlush(locataireBien);

        // Get the locataireBien
        restLocataireBienMockMvc.perform(get("/api/locataire-biens/{id}", locataireBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locataireBien.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLocataireBien() throws Exception {
        // Get the locataireBien
        restLocataireBienMockMvc.perform(get("/api/locataire-biens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocataireBien() throws Exception {
        // Initialize the database
        locataireBienRepository.saveAndFlush(locataireBien);
        int databaseSizeBeforeUpdate = locataireBienRepository.findAll().size();

        // Update the locataireBien
        LocataireBien updatedLocataireBien = locataireBienRepository.findOne(locataireBien.getId());

        restLocataireBienMockMvc.perform(put("/api/locataire-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocataireBien)))
            .andExpect(status().isOk());

        // Validate the LocataireBien in the database
        List<LocataireBien> locataireBienList = locataireBienRepository.findAll();
        assertThat(locataireBienList).hasSize(databaseSizeBeforeUpdate);
        LocataireBien testLocataireBien = locataireBienList.get(locataireBienList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingLocataireBien() throws Exception {
        int databaseSizeBeforeUpdate = locataireBienRepository.findAll().size();

        // Create the LocataireBien

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLocataireBienMockMvc.perform(put("/api/locataire-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locataireBien)))
            .andExpect(status().isCreated());

        // Validate the LocataireBien in the database
        List<LocataireBien> locataireBienList = locataireBienRepository.findAll();
        assertThat(locataireBienList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLocataireBien() throws Exception {
        // Initialize the database
        locataireBienRepository.saveAndFlush(locataireBien);
        int databaseSizeBeforeDelete = locataireBienRepository.findAll().size();

        // Get the locataireBien
        restLocataireBienMockMvc.perform(delete("/api/locataire-biens/{id}", locataireBien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LocataireBien> locataireBienList = locataireBienRepository.findAll();
        assertThat(locataireBienList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocataireBien.class);
        LocataireBien locataireBien1 = new LocataireBien();
        locataireBien1.setId(1L);
        LocataireBien locataireBien2 = new LocataireBien();
        locataireBien2.setId(locataireBien1.getId());
        assertThat(locataireBien1).isEqualTo(locataireBien2);
        locataireBien2.setId(2L);
        assertThat(locataireBien1).isNotEqualTo(locataireBien2);
        locataireBien1.setId(null);
        assertThat(locataireBien1).isNotEqualTo(locataireBien2);
    }
}
