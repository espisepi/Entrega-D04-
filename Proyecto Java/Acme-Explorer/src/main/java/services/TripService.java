
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
import domain.ApplicationFor;
import domain.AuditRecord;
import domain.Category;
import domain.LegalText;
import domain.Manager;
import domain.Note;
import domain.Ranger;
import domain.Sponsorship;
import domain.Stage;
import domain.Story;
import domain.SurvivalClass;
import domain.Tag;
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
	private ExplorerService	explorerService;
	private ActorService	actorService;


	//private LegalText		legalTextService;

	// Constructors------------------------------------------------------------
	public TripService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Trip create() {
		//Comprobamos que solo el manager sea el que puede crear Trip
		//Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER));
		this.managerService.checkPrincipal();
		final Collection<SurvivalClass> classes = new ArrayList<SurvivalClass>();
		final Collection<Story> stories = new ArrayList<Story>();
		final Collection<ApplicationFor> applicationsFor = new ArrayList<ApplicationFor>();
		final Collection<AuditRecord> auditRecords = new ArrayList<AuditRecord>();
		final Collection<Note> notes = new ArrayList<Note>();
		final Collection<Category> categories = new ArrayList<Category>();
		final Collection<Sponsorship> sponsorships = new ArrayList<Sponsorship>();
		final Collection<Stage> stages = new ArrayList<Stage>();
		final Collection<Tag> tags = new ArrayList<Tag>();
		LegalText legalText = new LegalText();
		Ranger ranger = new Ranger();
		Trip trip;
		trip = new Trip();
		Manager manager = this.managerService.findByPrincipal();
		trip.setManager(manager);
		trip.setRanger(ranger);
		trip.setRanger(ranger);
		trip.setClasses(classes);
		trip.setStories(stories);
		trip.setApplicationsFor(applicationsFor);
		trip.setAuditRecords(auditRecords);
		trip.setNotes(notes);
		trip.setCategories(categories);
		trip.setSponsorships(sponsorships);
		trip.setStages(stages);
		trip.setTags(tags);
		trip.setLegalText(legalText);

		return trip;
	}

	public Trip update(Trip trip) {
		assert trip != null;
		Date currentDate = new Date();
		//Si no tiene fecha de publicación podemos continuar editando
		Assert.isTrue(trip.getPublicationDate() == null);
		//Comprobamos que el trip aun no ha empezado
		Assert.isTrue(trip.getStartDate().after(currentDate));
		boolean isManager = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER);
		boolean isExplorer = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.EXPLORER);

		if (isManager) {
			//Saco los trips del manager
			Collection<Trip> trips = this.findAllTripsByManagerId(this.managerService.findByPrincipal().getId());
			//Compruebo que ese trip que quiera editar lo creó él
			Assert.isTrue(trips.contains(trip));
			trip.setCancelled(true);
		} else if (isExplorer) {
			//Saco los trips del explorer con estado ACCEPTED
			Collection<Trip> trips = this.findAllTripsByExplorerIdWithStatusAccepted(this.explorerService.findByPrincipal().getId());
			//Compruebo que ese trip que quiera editar lo organizó él
			Assert.isTrue(trips.contains(trip));
			trip.setCancelled(true);
		}
		Trip result;
		result = this.tripRepository.save(trip);
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
		//Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER));
		this.managerService.checkPrincipal();
		Assert.isTrue(trip.getPublicationDate() == null);
		//Saco los trips del manager
		Collection<Trip> trips = this.findAllTripsByManagerId(this.managerService.findByPrincipal().getId());
		Assert.isTrue(trips.contains(trip));

		this.tripRepository.delete(trip);
	}

	// Other business methods -------------------------------------------------
	public Collection<Trip> findAllTrips() {
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Collection<Trip> findAllTripsByManagerId(int managerId) {
		//Assert.notNull(managerId);
		Collection<Trip> res = new ArrayList<Trip>();
		res.addAll(this.tripRepository.findAllTripsByManagerId(managerId));
		Assert.notNull(res);
		return res;
	}

	public Collection<Trip> findAllTripsByExplorerIdWithStatusAccepted(int explorerId) {
		//Assert.notNull(explorerId);
		Collection<Trip> res = new ArrayList<Trip>();
		res.addAll(this.tripRepository.findAllTripsByExplorerIdWithStatusAccepted(explorerId));
		Assert.notNull(res);
		return res;
	}

	public Collection<Trip> findAllTripsPublishedNotStarted() {
		Collection<Trip> trips = new ArrayList<>();
		Collection<Trip> res = new ArrayList<>();
		Date currentDate = new Date();
		this.managerService.checkPrincipal();
		//Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER));
		res.addAll(this.tripRepository.findAllTripsPublishedNotStarted());
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
		Assert.isTrue(this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.EXPLORER));
		trips.addAll(this.tripRepository.findTripsWhitStatusAccepted());
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
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findAllTripsPublishedNotStarted());
		if (trips.contains(trip))
			trip.setCancelled(true);
	}

	public void cancelTripByExplorer(Trip trip) {
		Assert.notNull(trip);
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findTripsWhitStatusAccepted());
		if (trips.contains(trip))
			trip.setCancelled(true);
	}
	public Trip findTripWithStatusPendingById(int tripId) {
		Trip result = this.tripRepository.findTripWithStatusPendingById(tripId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Trip> findAllTripsApplyByExplorerId(int explorerId) {
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findAllTripsApplyByExplorerId(explorerId));
		Assert.notNull(trips);
		return trips;
	}
	public Collection<Trip> finAllTripsAuditByAuditorId(int auditorId) {
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findAllTripsByExplorerIdWithStatusAccepted(auditorId));
		Assert.notNull(trips);
		return trips;

	}
}
