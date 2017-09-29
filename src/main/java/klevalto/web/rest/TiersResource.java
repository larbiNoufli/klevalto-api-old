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
import klevalto.domain.Tiers;
import klevalto.repository.TiersRepository;
import klevalto.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Tiers.
 */
@RestController
@RequestMapping("/api")
public class TiersResource {

    private final Logger log = LoggerFactory.getLogger(TiersResource.class);

    private static final String ENTITY_NAME = "tiers";

    private final TiersRepository tiersRepository;

    public TiersResource(TiersRepository tiersRepository) {
        this.tiersRepository = tiersRepository;
    }

    /**
     * POST  /tiers : Create a new tiers.
     *
     * @param tiers the tiers to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tiers, or with status 400 (Bad Request) if the tiers has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tiers")
    @Timed
    public ResponseEntity<Tiers> createTiers(@RequestBody Tiers tiers) throws URISyntaxException {
        log.debug("REST request to save Tiers : {}", tiers);
        if (tiers.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tiers cannot already have an ID")).body(null);
        }
        Tiers result = tiersRepository.save(tiers);
        return ResponseEntity.created(new URI("/api/tiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tiers : Updates an existing tiers.
     *
     * @param tiers the tiers to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tiers,
     * or with status 400 (Bad Request) if the tiers is not valid,
     * or with status 500 (Internal Server Error) if the tiers couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tiers")
    @Timed
    public ResponseEntity<Tiers> updateTiers(@RequestBody Tiers tiers) throws URISyntaxException {
        log.debug("REST request to update Tiers : {}", tiers);
        if (tiers.getId() == null) {
            return createTiers(tiers);
        }
        Tiers result = tiersRepository.save(tiers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tiers.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tiers : get all the tiers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tiers in body
     */
    @GetMapping("/tiers")
    @Timed
    public List<Tiers> getAllTiers() {
        log.debug("REST request to get all Tiers");
        return tiersRepository.findAll();
        }

    /**
     * GET  /tiers/:id : get the "id" tiers.
     *
     * @param id the id of the tiers to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tiers, or with status 404 (Not Found)
     */
    @GetMapping("/tiers/{id}")
    @Timed
    public ResponseEntity<Tiers> getTiers(@PathVariable Long id) {
        log.debug("REST request to get Tiers : {}", id);
        Tiers tiers = tiersRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tiers));
    }

    /**
     * DELETE  /tiers/:id : delete the "id" tiers.
     *
     * @param id the id of the tiers to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tiers/{id}")
    @Timed
    public ResponseEntity<Void> deleteTiers(@PathVariable Long id) {
        log.debug("REST request to delete Tiers : {}", id);
        tiersRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
