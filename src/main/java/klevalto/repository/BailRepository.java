package klevalto.repository;

import klevalto.domain.Bail;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Bail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BailRepository extends JpaRepository<Bail, Long> {

}
