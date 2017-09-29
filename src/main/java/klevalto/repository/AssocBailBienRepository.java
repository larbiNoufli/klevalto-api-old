package klevalto.repository;

import klevalto.domain.AssocBailBien;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AssocBailBien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssocBailBienRepository extends JpaRepository<AssocBailBien, Long> {

}
