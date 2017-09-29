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
import klevalto.domain.UtilisationRibTiers;
import klevalto.repository.UtilisationRibTiersRepository;
import klevalto.web.rest.util.HeaderUtil;

/**
 * REST controller for managing UtilisationRibTiers.
 */
@RestController
@RequestMapping("/api")
public class UtilisationRibTiersResource {

    private final Logger log = LoggerFactory.getLogger(UtilisationRibTiersResource.class);

    private static final String ENTITY_NAME = "utilisationRibTiers";

    private final UtilisationRibTiersRepository utilisationRibTiersRepository;

    public UtilisationRibTiersResource(UtilisationRibTiersRepository utilisationRibTiersRepository) {
        this.utilisationRibTiersRepository = utilisationRibTiersRepository;
    }

    /**
     * POST  /utilisation-rib-tiers : Create a new utilisationRibTiers.
     *
     * @param utilisationRibTiers the utilisationRibTiers to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utilisationRibTiers, or with status 400 (Bad Request) if the utilisationRibTiers has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/utilisation-rib-tiers")
    @Timed
    public ResponseEntity<UtilisationRibTiers> createUtilisationRibTiers(@RequestBody UtilisationRibTiers utilisationRibTiers) throws URISyntaxException {
        log.debug("REST request to save UtilisationRibTiers : {}", utilisationRibTiers);
        if (utilisationRibTiers.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new utilisationRibTiers cannot already have an ID")).body(null);
        }
        UtilisationRibTiers result = utilisationRibTiersRepository.save(utilisationRibTiers);
        return ResponseEntity.created(new URI("/api/utilisation-rib-tiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /utilisation-rib-tiers : Updates an existing utilisationRibTiers.
     *
     * @param utilisationRibTiers the utilisationRibTiers to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utilisationRibTiers,
     * or with status 400 (Bad Request) if the utilisationRibTiers is not valid,
     * or with status 500 (Internal Server Error) if the utilisationRibTiers couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/utilisation-rib-tiers")
    @Timed
    public ResponseEntity<UtilisationRibTiers> updateUtilisationRibTiers(@RequestBody UtilisationRibTiers utilisationRibTiers) throws URISyntaxException {
        log.debug("REST request to update UtilisationRibTiers : {}", utilisationRibTiers);
        if (utilisationRibTiers.getId() == null) {
            return createUtilisationRibTiers(utilisationRibTiers);
        }
        UtilisationRibTiers result = utilisationRibTiersRepository.save(utilisationRibTiers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utilisationRibTiers.getId().toString()))
            .body(result);
    }

    /**
     * GET  /utilisation-rib-tiers : get all the utilisationRibTiers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of utilisationRibTiers in body
     */
    @GetMapping("/utilisation-rib-tiers")
    @Timed
    public List<UtilisationRibTiers> getAllUtilisationRibTiers() {
        log.debug("REST request to get all UtilisationRibTiers");
        return utilisationRibTiersRepository.findAll();
        }

    /**
     * GET  /utilisation-rib-tiers/:id : get the "id" utilisationRibTiers.
     *
     * @param id the id of the utilisationRibTiers to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utilisationRibTiers, or with status 404 (Not Found)
     */
    @GetMapping("/utilisation-rib-tiers/{id}")
    @Timed
    public ResponseEntity<UtilisationRibTiers> getUtilisationRibTiers(@PathVariable Long id) {
        log.debug("REST request to get UtilisationRibTiers : {}", id);
        UtilisationRibTiers utilisationRibTiers = utilisationRibTiersRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(utilisationRibTiers));
    }

    /**
     * DELETE  /utilisation-rib-tiers/:id : delete the "id" utilisationRibTiers.
     *
     * @param id the id of the utilisationRibTiers to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/utilisation-rib-tiers/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtilisationRibTiers(@PathVariable Long id) {
        log.debug("REST request to delete UtilisationRibTiers : {}", id);
        utilisationRibTiersRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
