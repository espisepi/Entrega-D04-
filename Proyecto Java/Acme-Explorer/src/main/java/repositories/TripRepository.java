
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Explorer;
import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	//Devolvemos todos los viajes PARA CUALQUIERA que NO esté autenticado y
	//que no estén cancelados y además tenga fecha de publicación.
	@Query("select t from Trip t where t.publicationDate!=null and t.cancelled=false")
	Collection<Trip> findAllTripsNoAuthenticate();

	@Query("select t from Trip t where t.publicationDate!=null")
	Collection<Trip> findAllTripsPublished();

	@Query("select t from Trip t join t.applicationsFor a where a.status like 'ACCEPTED'")
	Collection<Trip> findTripsWhitStatusAccepted();

	@Query("select t from Trip t join t.applicationsFor a where a.explorer.id=?1")
	Collection<Trip> findAllTripsApplyByExplorerId(int explorerId);

	@Query("select t from Trip t join t.auditRecords a where a.auditor.id=?1")
	Collection<Trip> findByAuditorId(int auditorId);

	@Query("select e from Explorer e join e.applicationsFor a where a.trip.id=?1")
	Collection<Explorer> findExplorersByTripId(int tripId);
}
