
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
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


	// Constructors------------------------------------------------------------
	public TripService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Trip create() {
		//Assert.notNull(managerService.findByPrincipal());
		Trip result;
		result = new Trip();
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
		assert trip != null;
		assert trip.getId() != 0;

		Assert.isTrue(this.tripRepository.exists(trip.getId()));

		this.tripRepository.delete(trip);
	}
}
