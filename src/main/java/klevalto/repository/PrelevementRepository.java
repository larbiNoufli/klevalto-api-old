package klevalto.repository;

import klevalto.domain.Prelevement;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Prelevement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrelevementRepository extends JpaRepository<Prelevement, Long> {

}
