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
import klevalto.domain.AdressePostale;
import klevalto.repository.AdressePostaleRepository;
import klevalto.web.rest.util.HeaderUtil;

/**
 * REST controller for managing AdressePostale.
 */
@RestController
@RequestMapping("/api")
public class AdressePostaleResource {

    private final Logger log = LoggerFactory.getLogger(AdressePostaleResource.class);

    private static final String ENTITY_NAME = "adressePostale";

    private final AdressePostaleRepository adressePostaleRepository;

    public AdressePostaleResource(AdressePostaleRepository adressePostaleRepository) {
        this.adressePostaleRepository = adressePostaleRepository;
    }

    /**
     * POST  /adresse-postales : Create a new adressePostale.
     *
     * @param adressePostale the adressePostale to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adressePostale, or with status 400 (Bad Request) if the adressePostale has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adresse-postales")
    @Timed
    public ResponseEntity<AdressePostale> createAdressePostale(@RequestBody AdressePostale adressePostale) throws URISyntaxException {
        log.debug("REST request to save AdressePostale : {}", adressePostale);
        if (adressePostale.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new adressePostale cannot already have an ID")).body(null);
        }
        AdressePostale result = adressePostaleRepository.save(adressePostale);
        return ResponseEntity.created(new URI("/api/adresse-postales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adresse-postales : Updates an existing adressePostale.
     *
     * @param adressePostale the adressePostale to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adressePostale,
     * or with status 400 (Bad Request) if the adressePostale is not valid,
     * or with status 500 (Internal Server Error) if the adressePostale couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adresse-postales")
    @Timed
    public ResponseEntity<AdressePostale> updateAdressePostale(@RequestBody AdressePostale adressePostale) throws URISyntaxException {
        log.debug("REST request to update AdressePostale : {}", adressePostale);
        if (adressePostale.getId() == null) {
            return createAdressePostale(adressePostale);
        }
        AdressePostale result = adressePostaleRepository.save(adressePostale);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adressePostale.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adresse-postales : get all the adressePostales.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of adressePostales in body
     */
    @GetMapping("/adresse-postales")
    @Timed
    public List<AdressePostale> getAllAdressePostales() {
        log.debug("REST request to get all AdressePostales");
        return adressePostaleRepository.findAll();
        }

    /**
     * GET  /adresse-postales/:id : get the "id" adressePostale.
     *
     * @param id the id of the adressePostale to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adressePostale, or with status 404 (Not Found)
     */
    @GetMapping("/adresse-postales/{id}")
    @Timed
    public ResponseEntity<AdressePostale> getAdressePostale(@PathVariable Long id) {
        log.debug("REST request to get AdressePostale : {}", id);
        AdressePostale adressePostale = adressePostaleRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(adressePostale));
    }

    /**
     * DELETE  /adresse-postales/:id : delete the "id" adressePostale.
     *
     * @param id the id of the adressePostale to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adresse-postales/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdressePostale(@PathVariable Long id) {
        log.debug("REST request to delete AdressePostale : {}", id);
        adressePostaleRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
