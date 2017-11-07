
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	//Devolvemos todos los viajes PARA CUALQUIERA que NO est� autenticado y
	//que no est�n cancelados y adem�s tenga fecha de publicaci�n.
	@Query("select count(t) from Trip t where t.publicationDate!=null and t.cancelled=false")
	Collection<Trip> findAllTrips();

	@Query("select t from Trip t where t.manager.id= ?1")
	Collection<Trip> findAllTripsByManagerId(int managerId);

	@Query("select count(t) from Trip t where t.publicationDate!=null")
	Collection<Trip> findAllTripsPublishedNotStarted();

	@Query("select t from Trip t join t.applicationsFor a where a.status like 'ACCEPTED'")
	Collection<Trip> findTripsWhitStatusAccepted();

	@Query("select t from Trip t join t.applicationsFor a where a.status like 'PENDING' and t.id= ?1")
	Trip findTripWithStatusPendingById(int tripId);
}
