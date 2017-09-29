package klevalto.repository;

import klevalto.domain.Pole;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Pole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoleRepository extends JpaRepository<Pole, Long> {

}
