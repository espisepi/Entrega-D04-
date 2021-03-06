
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
	//Requisito 10.2
	@Query("select t from Trip t where t.publicationDate!=null and t.cancelled=false")
	Collection<Trip> findAllTripsNoAuthenticate();

	//Requisito 12.1
	@Query("select t from Trip t where t.publicationDate=null")
	Collection<Trip> findAllTripsNotPublished();

	//Requisito 12.3
	@Query(" select t from Trip t where t.publicationDate!=null and t.startDate>CURRENT_TIMESTAMP")
	Collection<Trip> findAllTripsPublishedNotStarted();

	//Requisito 13.4
	@Query("select t from Trip t join t.applicationsFor a where a.status='ACCEPTED' and t.startDate>CURRENT_TIMESTAMP")
	Collection<Trip> findTripsWhitStatusAcceptedNotStarted();

	//Requisito de informaci�n C/5.
	@Query("select t from Trip t join t.applicationsFor a where a.explorer.id=?1")
	Collection<Trip> findAllTripsApplyByExplorerId(int explorerId);

	//Requisito de Informaci�n B/10.
	@Query("select t from Trip t join t.auditRecords a where a.auditor.id=?1")
	Collection<Trip> findByAuditorId(int auditorId);

	//Suma todos los precios con iva de las stages de la trip pasada como parametro.
	@Query("select sum(c.totalPrice) from Trip r join r.stages c where r.id=?1")
	Double findPrice(int tripId);

	@Query("select t from Trip t join t.categories c where c.id=?1")
	Collection<Trip> findAllTripsByCategoryId(int categoryId);
}
