package klevalto.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import klevalto.domain.Prelevement;
import klevalto.repository.PrelevementRepository;
import klevalto.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Prelevement.
 */
@RestController
@RequestMapping("/api")
public class PrelevementResource {

    private final Logger log = LoggerFactory.getLogger(PrelevementResource.class);

    private static final String ENTITY_NAME = "prelevement";

    private final PrelevementRepository prelevementRepository;

    public PrelevementResource(PrelevementRepository prelevementRepository) {
        this.prelevementRepository = prelevementRepository;
    }

    /**
     * POST  /prelevements : Create a new prelevement.
     *
     * @param prelevement the prelevement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prelevement, or with status 400 (Bad Request) if the prelevement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/prelevements")
    @Timed
    public ResponseEntity<Prelevement> createPrelevement(@RequestBody Prelevement prelevement) throws URISyntaxException {
        log.debug("REST request to save Prelevement : {}", prelevement);
        if (prelevement.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new prelevement cannot already have an ID")).body(null);
        }
        Prelevement result = prelevementRepository.save(prelevement);
        return ResponseEntity.created(new URI("/api/prelevements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /prelevements : Updates an existing prelevement.
     *
     * @param prelevement the prelevement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prelevement,
     * or with status 400 (Bad Request) if the prelevement is not valid,
     * or with status 500 (Internal Server Error) if the prelevement couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/prelevements")
    @Timed
    public ResponseEntity<Prelevement> updatePrelevement(@RequestBody Prelevement prelevement) throws URISyntaxException {
        log.debug("REST request to update Prelevement : {}", prelevement);
        if (prelevement.getId() == null) {
            return createPrelevement(prelevement);
        }
        Prelevement result = prelevementRepository.save(prelevement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prelevement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /prelevements : get all the prelevements.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of prelevements in body
     */
    @GetMapping("/prelevements")
    @Timed
    public List<Prelevement> getAllPrelevements() {
        log.debug("REST request to get all Prelevements");
        return prelevementRepository.findAll();
        }

    /**
     * GET  /prelevements/:id : get the "id" prelevement.
     *
     * @param id the id of the prelevement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prelevement, or with status 404 (Not Found)
     */
    @GetMapping("/prelevements/{id}")
    @Timed
    public ResponseEntity<Prelevement> getPrelevement(@PathVariable Long id) {
        log.debug("REST request to get Prelevement : {}", id);
        Prelevement prelevement = prelevementRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prelevement));
    }

    /**
     * DELETE  /prelevements/:id : delete the "id" prelevement.
     *
     * @param id the id of the prelevement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/prelevements/{id}")
    @Timed
    public ResponseEntity<Void> deletePrelevement(@PathVariable Long id) {
        log.debug("REST request to delete Prelevement : {}", id);
        prelevementRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
