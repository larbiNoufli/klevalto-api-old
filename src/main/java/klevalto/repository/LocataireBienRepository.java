package klevalto.repository;

import klevalto.domain.LocataireBien;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LocataireBien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocataireBienRepository extends JpaRepository<LocataireBien, Long> {

}
