
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Explorer;

@Repository
public interface ExplorerRepository extends JpaRepository<Explorer, Integer> {

	//Explorer autentificado en el sistema
	@Query("select e from Explorer e where e.userAccount.id=?1")
	Explorer findByUserAccountId(int userAccountId);

	//Lista de explorer que han solicitado ese trip.
	@Query("select e from Explorer e join e.applicationsFor a where a.trip.id=?1")
	Collection<Explorer> findExplorersByTripId(int tripId);

	@Query("select e from Explorer e join e.contactsEmergency c where c.id=?1")
	Collection<Explorer> findExplorersByContactEmergencyId(int contactEmergencyId);

}
