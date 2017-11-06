
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
import security.Authority;
import domain.Trip;

@Service
@Transactional
public class TripService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private TripRepository	tripRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ManagerService	managerService;
	private ActorService	actorService;


	// Constructors------------------------------------------------------------
	public TripService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Trip create() {
		//Comprobamos que solo el manager sea el que puede crear Trip
		Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER));
		Trip result;
		result = new Trip();

		//result.setPrice(totalPrice);
		return result;
	}

	public Collection<Trip> findAll() {
		Collection<Trip> result;
		Assert.notNull(this.tripRepository);
		result = this.tripRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Trip findOne(final int tripId) {
		Trip result;
		Date publicationDate;
		//TODO: ¿Quien lo crea?-> Manager

		result = this.tripRepository.findOne(tripId);
		publicationDate = new Date(System.currentTimeMillis() - 1000);

		result.setPublicationDate(publicationDate);
		return result;
	}

	public Trip save(final Trip trip) {
		assert trip != null;
		Trip result;
		result = this.tripRepository.save(trip);
		return result;
	}

	public void delete(final Trip trip) {
		Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER));
		assert trip != null;
		assert trip.getId() != 0;
		Assert.isTrue(trip.getPublicationDate() != null);

		Assert.isTrue(this.tripRepository.exists(trip.getId()));

		this.tripRepository.delete(trip);
	}

	// Other business methods -------------------------------------------------
	public Collection<Trip> findAllTrips() {
		Collection<Trip> res;
		res = this.tripRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Collection<Trip> findAllTripsByManagerId(int managerId) {
		Collection<Trip> res;
		res = this.tripRepository.findAllTripsByManagerId(managerId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Trip> findAllTripsPublishedNotStarted() {
		Collection<Trip> trips = new ArrayList<>();
		Collection<Trip> res = new ArrayList<>();
		Date currentDate = new Date();
		Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER));
		res = this.tripRepository.findAllTripsPublishedNotStarted();
		Assert.notNull(res);

		for (Trip t : trips)
			if (t.getStartDate().after(currentDate) == true)
				res.add(t);
		return res;
	}

	public Collection<Trip> findTripsWhitStatusAccepted() {
		Collection<Trip> trips = new ArrayList<>();
		Collection<Trip> res = new ArrayList<>();
		Date currentDate = new Date();
		Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.EXPLORER));
		trips = this.tripRepository.findTripsWhitStatusAccepted();
		Assert.notNull(res);
		for (Trip t : trips)
			if (t.getStartDate().after(currentDate) == true)
				res.add(t);
		return res;
	}

	//Metodo cancelar
	//llamar a findall... para conseguir los que no se han publicad
	//contemplar que es manager
	//cancelar

	public void cancelTripByManager(Trip trip) {
		Assert.notNull(trip);
		Collection<Trip> trips = new ArrayList<Trip>(this.findAllTripsPublishedNotStarted());
		if (trips.contains(trip))
			trip.setCancelled(true);
	}

	public void cancelTripByExplorer(Trip trip) {
		Assert.notNull(trip);
		Collection<Trip> trips = new ArrayList<Trip>(this.findTripsWhitStatusAccepted());
		if (trips.contains(trip))
			trip.setCancelled(true);
	}

}
