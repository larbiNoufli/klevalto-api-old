package klevalto.web.rest;

import com.codahale.metrics.annotation.Timed;
import klevalto.domain.Mandat;

import klevalto.repository.MandatRepository;
import klevalto.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Mandat.
 */
@RestController
@RequestMapping("/api")
public class MandatResource {

    private final Logger log = LoggerFactory.getLogger(MandatResource.class);

    private static final String ENTITY_NAME = "mandat";

    private final MandatRepository mandatRepository;

    public MandatResource(MandatRepository mandatRepository) {
        this.mandatRepository = mandatRepository;
    }

    /**
     * POST  /mandats : Create a new mandat.
     *
     * @param mandat the mandat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mandat, or with status 400 (Bad Request) if the mandat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mandats")
    @Timed
    public ResponseEntity<Mandat> createMandat(@RequestBody Mandat mandat) throws URISyntaxException {
        log.debug("REST request to save Mandat : {}", mandat);
        if (mandat.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new mandat cannot already have an ID")).body(null);
        }
        Mandat result = mandatRepository.save(mandat);
        return ResponseEntity.created(new URI("/api/mandats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mandats : Updates an existing mandat.
     *
     * @param mandat the mandat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mandat,
     * or with status 400 (Bad Request) if the mandat is not valid,
     * or with status 500 (Internal Server Error) if the mandat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mandats")
    @Timed
    public ResponseEntity<Mandat> updateMandat(@RequestBody Mandat mandat) throws URISyntaxException {
        log.debug("REST request to update Mandat : {}", mandat);
        if (mandat.getId() == null) {
            return createMandat(mandat);
        }
        Mandat result = mandatRepository.save(mandat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mandat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mandats : get all the mandats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mandats in body
     */
    @GetMapping("/mandats")
    @Timed
    public List<Mandat> getAllMandats() {
        log.debug("REST request to get all Mandats");
        return mandatRepository.findAll();
        }

    /**
     * GET  /mandats/:id : get the "id" mandat.
     *
     * @param id the id of the mandat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mandat, or with status 404 (Not Found)
     */
    @GetMapping("/mandats/{id}")
    @Timed
    public ResponseEntity<Mandat> getMandat(@PathVariable Long id) {
        log.debug("REST request to get Mandat : {}", id);
        Mandat mandat = mandatRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(mandat));
    }

    /**
     * DELETE  /mandats/:id : delete the "id" mandat.
     *
     * @param id the id of the mandat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mandats/{id}")
    @Timed
    public ResponseEntity<Void> deleteMandat(@PathVariable Long id) {
        log.debug("REST request to delete Mandat : {}", id);
        mandatRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
