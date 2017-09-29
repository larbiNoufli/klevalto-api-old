package klevalto.repository;

import klevalto.domain.AdressePostale;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AdressePostale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdressePostaleRepository extends JpaRepository<AdressePostale, Long> {

}
