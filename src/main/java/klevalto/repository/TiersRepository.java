package klevalto.repository;

import klevalto.domain.Tiers;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Tiers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TiersRepository extends JpaRepository<Tiers, Long> {

}
