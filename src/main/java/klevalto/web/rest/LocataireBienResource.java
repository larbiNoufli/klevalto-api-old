package klevalto.web.rest;

import com.codahale.metrics.annotation.Timed;
import klevalto.domain.LocataireBien;

import klevalto.repository.LocataireBienRepository;
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
 * REST controller for managing LocataireBien.
 */
@RestController
@RequestMapping("/api")
public class LocataireBienResource {

    private final Logger log = LoggerFactory.getLogger(LocataireBienResource.class);

    private static final String ENTITY_NAME = "locataireBien";

    private final LocataireBienRepository locataireBienRepository;

    public LocataireBienResource(LocataireBienRepository locataireBienRepository) {
        this.locataireBienRepository = locataireBienRepository;
    }

    /**
     * POST  /locataire-biens : Create a new locataireBien.
     *
     * @param locataireBien the locataireBien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new locataireBien, or with status 400 (Bad Request) if the locataireBien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/locataire-biens")
    @Timed
    public ResponseEntity<LocataireBien> createLocataireBien(@RequestBody LocataireBien locataireBien) throws URISyntaxException {
        log.debug("REST request to save LocataireBien : {}", locataireBien);
        if (locataireBien.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new locataireBien cannot already have an ID")).body(null);
        }
        LocataireBien result = locataireBienRepository.save(locataireBien);
        return ResponseEntity.created(new URI("/api/locataire-biens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /locataire-biens : Updates an existing locataireBien.
     *
     * @param locataireBien the locataireBien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated locataireBien,
     * or with status 400 (Bad Request) if the locataireBien is not valid,
     * or with status 500 (Internal Server Error) if the locataireBien couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/locataire-biens")
    @Timed
    public ResponseEntity<LocataireBien> updateLocataireBien(@RequestBody LocataireBien locataireBien) throws URISyntaxException {
        log.debug("REST request to update LocataireBien : {}", locataireBien);
        if (locataireBien.getId() == null) {
            return createLocataireBien(locataireBien);
        }
        LocataireBien result = locataireBienRepository.save(locataireBien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, locataireBien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /locataire-biens : get all the locataireBiens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of locataireBiens in body
     */
    @GetMapping("/locataire-biens")
    @Timed
    public List<LocataireBien> getAllLocataireBiens() {
        log.debug("REST request to get all LocataireBiens");
        return locataireBienRepository.findAll();
        }

    /**
     * GET  /locataire-biens/:id : get the "id" locataireBien.
     *
     * @param id the id of the locataireBien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the locataireBien, or with status 404 (Not Found)
     */
    @GetMapping("/locataire-biens/{id}")
    @Timed
    public ResponseEntity<LocataireBien> getLocataireBien(@PathVariable Long id) {
        log.debug("REST request to get LocataireBien : {}", id);
        LocataireBien locataireBien = locataireBienRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(locataireBien));
    }

    /**
     * DELETE  /locataire-biens/:id : delete the "id" locataireBien.
     *
     * @param id the id of the locataireBien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/locataire-biens/{id}")
    @Timed
    public ResponseEntity<Void> deleteLocataireBien(@PathVariable Long id) {
        log.debug("REST request to delete LocataireBien : {}", id);
        locataireBienRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
