package klevalto.repository;

import klevalto.domain.Mandat;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Mandat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MandatRepository extends JpaRepository<Mandat, Long> {

}
