package klevalto.repository;

import klevalto.domain.UtilisationRibTiers;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UtilisationRibTiers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtilisationRibTiersRepository extends JpaRepository<UtilisationRibTiers, Long> {

}
