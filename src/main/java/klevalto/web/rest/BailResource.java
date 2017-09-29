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
import klevalto.domain.Bail;
import klevalto.repository.BailRepository;
import klevalto.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Bail.
 */
@RestController
@RequestMapping("/api")
public class BailResource {

    private final Logger log = LoggerFactory.getLogger(BailResource.class);

    private static final String ENTITY_NAME = "bail";

    private final BailRepository bailRepository;

    public BailResource(BailRepository bailRepository) {
        this.bailRepository = bailRepository;
    }

    /**
     * POST  /bails : Create a new bail.
     *
     * @param bail the bail to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bail, or with status 400 (Bad Request) if the bail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bails")
    @Timed
    public ResponseEntity<Bail> createBail(@RequestBody Bail bail) throws URISyntaxException {
        log.debug("REST request to save Bail : {}", bail);
        if (bail.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bail cannot already have an ID")).body(null);
        }
        Bail result = bailRepository.save(bail);
        return ResponseEntity.created(new URI("/api/bails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bails : Updates an existing bail.
     *
     * @param bail the bail to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bail,
     * or with status 400 (Bad Request) if the bail is not valid,
     * or with status 500 (Internal Server Error) if the bail couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bails")
    @Timed
    public ResponseEntity<Bail> updateBail(@RequestBody Bail bail) throws URISyntaxException {
        log.debug("REST request to update Bail : {}", bail);
        if (bail.getId() == null) {
            return createBail(bail);
        }
        Bail result = bailRepository.save(bail);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bail.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bails : get all the bails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bails in body
     */
    @GetMapping("/bails")
    @Timed
    public List<Bail> getAllBails() {
        log.debug("REST request to get all Bails");
        return bailRepository.findAll();
        }

    /**
     * GET  /bails/:id : get the "id" bail.
     *
     * @param id the id of the bail to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bail, or with status 404 (Not Found)
     */
    @GetMapping("/bails/{id}")
    @Timed
    public ResponseEntity<Bail> getBail(@PathVariable Long id) {
        log.debug("REST request to get Bail : {}", id);
        Bail bail = bailRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bail));
    }

    /**
     * DELETE  /bails/:id : delete the "id" bail.
     *
     * @param id the id of the bail to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bails/{id}")
    @Timed
    public ResponseEntity<Void> deleteBail(@PathVariable Long id) {
        log.debug("REST request to delete Bail : {}", id);
        bailRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
