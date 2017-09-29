package klevalto.web.rest;

import com.codahale.metrics.annotation.Timed;
import klevalto.domain.Pole;

import klevalto.repository.PoleRepository;
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
 * REST controller for managing Pole.
 */
@RestController
@RequestMapping("/api")
public class PoleResource {

    private final Logger log = LoggerFactory.getLogger(PoleResource.class);

    private static final String ENTITY_NAME = "pole";

    private final PoleRepository poleRepository;

    public PoleResource(PoleRepository poleRepository) {
        this.poleRepository = poleRepository;
    }

    /**
     * POST  /poles : Create a new pole.
     *
     * @param pole the pole to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pole, or with status 400 (Bad Request) if the pole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poles")
    @Timed
    public ResponseEntity<Pole> createPole(@RequestBody Pole pole) throws URISyntaxException {
        log.debug("REST request to save Pole : {}", pole);
        if (pole.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pole cannot already have an ID")).body(null);
        }
        Pole result = poleRepository.save(pole);
        return ResponseEntity.created(new URI("/api/poles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /poles : Updates an existing pole.
     *
     * @param pole the pole to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pole,
     * or with status 400 (Bad Request) if the pole is not valid,
     * or with status 500 (Internal Server Error) if the pole couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poles")
    @Timed
    public ResponseEntity<Pole> updatePole(@RequestBody Pole pole) throws URISyntaxException {
        log.debug("REST request to update Pole : {}", pole);
        if (pole.getId() == null) {
            return createPole(pole);
        }
        Pole result = poleRepository.save(pole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pole.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poles : get all the poles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of poles in body
     */
    @GetMapping("/poles")
    @Timed
    public List<Pole> getAllPoles() {
        log.debug("REST request to get all Poles");
        return poleRepository.findAll();
        }

    /**
     * GET  /poles/:id : get the "id" pole.
     *
     * @param id the id of the pole to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pole, or with status 404 (Not Found)
     */
    @GetMapping("/poles/{id}")
    @Timed
    public ResponseEntity<Pole> getPole(@PathVariable Long id) {
        log.debug("REST request to get Pole : {}", id);
        Pole pole = poleRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pole));
    }

    /**
     * DELETE  /poles/:id : delete the "id" pole.
     *
     * @param id the id of the pole to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poles/{id}")
    @Timed
    public ResponseEntity<Void> deletePole(@PathVariable Long id) {
        log.debug("REST request to delete Pole : {}", id);
        poleRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
