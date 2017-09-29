package klevalto.web.rest;

import com.codahale.metrics.annotation.Timed;
import klevalto.domain.AssocMandatBien;

import klevalto.repository.AssocMandatBienRepository;
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
 * REST controller for managing AssocMandatBien.
 */
@RestController
@RequestMapping("/api")
public class AssocMandatBienResource {

    private final Logger log = LoggerFactory.getLogger(AssocMandatBienResource.class);

    private static final String ENTITY_NAME = "assocMandatBien";

    private final AssocMandatBienRepository assocMandatBienRepository;

    public AssocMandatBienResource(AssocMandatBienRepository assocMandatBienRepository) {
        this.assocMandatBienRepository = assocMandatBienRepository;
    }

    /**
     * POST  /assoc-mandat-biens : Create a new assocMandatBien.
     *
     * @param assocMandatBien the assocMandatBien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assocMandatBien, or with status 400 (Bad Request) if the assocMandatBien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assoc-mandat-biens")
    @Timed
    public ResponseEntity<AssocMandatBien> createAssocMandatBien(@RequestBody AssocMandatBien assocMandatBien) throws URISyntaxException {
        log.debug("REST request to save AssocMandatBien : {}", assocMandatBien);
        if (assocMandatBien.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new assocMandatBien cannot already have an ID")).body(null);
        }
        AssocMandatBien result = assocMandatBienRepository.save(assocMandatBien);
        return ResponseEntity.created(new URI("/api/assoc-mandat-biens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assoc-mandat-biens : Updates an existing assocMandatBien.
     *
     * @param assocMandatBien the assocMandatBien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assocMandatBien,
     * or with status 400 (Bad Request) if the assocMandatBien is not valid,
     * or with status 500 (Internal Server Error) if the assocMandatBien couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assoc-mandat-biens")
    @Timed
    public ResponseEntity<AssocMandatBien> updateAssocMandatBien(@RequestBody AssocMandatBien assocMandatBien) throws URISyntaxException {
        log.debug("REST request to update AssocMandatBien : {}", assocMandatBien);
        if (assocMandatBien.getId() == null) {
            return createAssocMandatBien(assocMandatBien);
        }
        AssocMandatBien result = assocMandatBienRepository.save(assocMandatBien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assocMandatBien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assoc-mandat-biens : get all the assocMandatBiens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of assocMandatBiens in body
     */
    @GetMapping("/assoc-mandat-biens")
    @Timed
    public List<AssocMandatBien> getAllAssocMandatBiens() {
        log.debug("REST request to get all AssocMandatBiens");
        return assocMandatBienRepository.findAll();
        }

    /**
     * GET  /assoc-mandat-biens/:id : get the "id" assocMandatBien.
     *
     * @param id the id of the assocMandatBien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assocMandatBien, or with status 404 (Not Found)
     */
    @GetMapping("/assoc-mandat-biens/{id}")
    @Timed
    public ResponseEntity<AssocMandatBien> getAssocMandatBien(@PathVariable Long id) {
        log.debug("REST request to get AssocMandatBien : {}", id);
        AssocMandatBien assocMandatBien = assocMandatBienRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assocMandatBien));
    }

    /**
     * DELETE  /assoc-mandat-biens/:id : delete the "id" assocMandatBien.
     *
     * @param id the id of the assocMandatBien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/assoc-mandat-biens/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssocMandatBien(@PathVariable Long id) {
        log.debug("REST request to delete AssocMandatBien : {}", id);
        assocMandatBienRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
