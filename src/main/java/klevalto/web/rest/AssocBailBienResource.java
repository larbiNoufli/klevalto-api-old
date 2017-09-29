package klevalto.web.rest;

import com.codahale.metrics.annotation.Timed;
import klevalto.domain.AssocBailBien;

import klevalto.repository.AssocBailBienRepository;
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
 * REST controller for managing AssocBailBien.
 */
@RestController
@RequestMapping("/api")
public class AssocBailBienResource {

    private final Logger log = LoggerFactory.getLogger(AssocBailBienResource.class);

    private static final String ENTITY_NAME = "assocBailBien";

    private final AssocBailBienRepository assocBailBienRepository;

    public AssocBailBienResource(AssocBailBienRepository assocBailBienRepository) {
        this.assocBailBienRepository = assocBailBienRepository;
    }

    /**
     * POST  /assoc-bail-biens : Create a new assocBailBien.
     *
     * @param assocBailBien the assocBailBien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assocBailBien, or with status 400 (Bad Request) if the assocBailBien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assoc-bail-biens")
    @Timed
    public ResponseEntity<AssocBailBien> createAssocBailBien(@RequestBody AssocBailBien assocBailBien) throws URISyntaxException {
        log.debug("REST request to save AssocBailBien : {}", assocBailBien);
        if (assocBailBien.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new assocBailBien cannot already have an ID")).body(null);
        }
        AssocBailBien result = assocBailBienRepository.save(assocBailBien);
        return ResponseEntity.created(new URI("/api/assoc-bail-biens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assoc-bail-biens : Updates an existing assocBailBien.
     *
     * @param assocBailBien the assocBailBien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assocBailBien,
     * or with status 400 (Bad Request) if the assocBailBien is not valid,
     * or with status 500 (Internal Server Error) if the assocBailBien couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assoc-bail-biens")
    @Timed
    public ResponseEntity<AssocBailBien> updateAssocBailBien(@RequestBody AssocBailBien assocBailBien) throws URISyntaxException {
        log.debug("REST request to update AssocBailBien : {}", assocBailBien);
        if (assocBailBien.getId() == null) {
            return createAssocBailBien(assocBailBien);
        }
        AssocBailBien result = assocBailBienRepository.save(assocBailBien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assocBailBien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assoc-bail-biens : get all the assocBailBiens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of assocBailBiens in body
     */
    @GetMapping("/assoc-bail-biens")
    @Timed
    public List<AssocBailBien> getAllAssocBailBiens() {
        log.debug("REST request to get all AssocBailBiens");
        return assocBailBienRepository.findAll();
        }

    /**
     * GET  /assoc-bail-biens/:id : get the "id" assocBailBien.
     *
     * @param id the id of the assocBailBien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assocBailBien, or with status 404 (Not Found)
     */
    @GetMapping("/assoc-bail-biens/{id}")
    @Timed
    public ResponseEntity<AssocBailBien> getAssocBailBien(@PathVariable Long id) {
        log.debug("REST request to get AssocBailBien : {}", id);
        AssocBailBien assocBailBien = assocBailBienRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assocBailBien));
    }

    /**
     * DELETE  /assoc-bail-biens/:id : delete the "id" assocBailBien.
     *
     * @param id the id of the assocBailBien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/assoc-bail-biens/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssocBailBien(@PathVariable Long id) {
        log.debug("REST request to delete AssocBailBien : {}", id);
        assocBailBienRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
