package klevalto.repository;

import klevalto.domain.RibTiers;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RibTiers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RibTiersRepository extends JpaRepository<RibTiers, Long> {

}
