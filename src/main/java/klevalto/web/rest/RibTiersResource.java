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
import klevalto.domain.RibTiers;
import klevalto.repository.RibTiersRepository;
import klevalto.web.rest.util.HeaderUtil;

/**
 * REST controller for managing RibTiers.
 */
@RestController
@RequestMapping("/api")
public class RibTiersResource {

    private final Logger log = LoggerFactory.getLogger(RibTiersResource.class);

    private static final String ENTITY_NAME = "ribTiers";

    private final RibTiersRepository ribTiersRepository;

    public RibTiersResource(RibTiersRepository ribTiersRepository) {
        this.ribTiersRepository = ribTiersRepository;
    }

    /**
     * POST  /rib-tiers : Create a new ribTiers.
     *
     * @param ribTiers the ribTiers to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ribTiers, or with status 400 (Bad Request) if the ribTiers has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rib-tiers")
    @Timed
    public ResponseEntity<RibTiers> createRibTiers(@RequestBody RibTiers ribTiers) throws URISyntaxException {
        log.debug("REST request to save RibTiers : {}", ribTiers);
        if (ribTiers.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ribTiers cannot already have an ID")).body(null);
        }
        RibTiers result = ribTiersRepository.save(ribTiers);
        return ResponseEntity.created(new URI("/api/rib-tiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rib-tiers : Updates an existing ribTiers.
     *
     * @param ribTiers the ribTiers to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ribTiers,
     * or with status 400 (Bad Request) if the ribTiers is not valid,
     * or with status 500 (Internal Server Error) if the ribTiers couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rib-tiers")
    @Timed
    public ResponseEntity<RibTiers> updateRibTiers(@RequestBody RibTiers ribTiers) throws URISyntaxException {
        log.debug("REST request to update RibTiers : {}", ribTiers);
        if (ribTiers.getId() == null) {
            return createRibTiers(ribTiers);
        }
        RibTiers result = ribTiersRepository.save(ribTiers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ribTiers.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rib-tiers : get all the ribTiers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ribTiers in body
     */
    @GetMapping("/rib-tiers")
    @Timed
    public List<RibTiers> getAllRibTiers() {
        log.debug("REST request to get all RibTiers");
        return ribTiersRepository.findAll();
        }

    /**
     * GET  /rib-tiers/:id : get the "id" ribTiers.
     *
     * @param id the id of the ribTiers to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ribTiers, or with status 404 (Not Found)
     */
    @GetMapping("/rib-tiers/{id}")
    @Timed
    public ResponseEntity<RibTiers> getRibTiers(@PathVariable Long id) {
        log.debug("REST request to get RibTiers : {}", id);
        RibTiers ribTiers = ribTiersRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ribTiers));
    }

    /**
     * DELETE  /rib-tiers/:id : delete the "id" ribTiers.
     *
     * @param id the id of the ribTiers to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rib-tiers/{id}")
    @Timed
    public ResponseEntity<Void> deleteRibTiers(@PathVariable Long id) {
        log.debug("REST request to delete RibTiers : {}", id);
        ribTiersRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
