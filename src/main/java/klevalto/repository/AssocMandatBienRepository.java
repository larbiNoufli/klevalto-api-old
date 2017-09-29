package klevalto.repository;

import klevalto.domain.AssocMandatBien;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AssocMandatBien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssocMandatBienRepository extends JpaRepository<AssocMandatBien, Long> {

}
