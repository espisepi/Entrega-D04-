
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
import domain.Explorer;
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
	@Autowired
	private ExplorerService	explorerService;
	@Autowired
	private ActorService	actorService;


	// Constructors------------------------------------------------------------
	public TripService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Trip create(Manager manager) {
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
		trip.setManager(manager);
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
		manager.getTrips().add(trip);

		return trip;
	}

	public Trip save(final Trip trip) {
		assert trip != null;
		Trip result;
		result = this.tripRepository.save(trip);
		return result;
	}

	/*
	 * public Trip update(Trip trip) {
	 * assert trip != null;
	 * Date currentDate = new Date();
	 * //Si no tiene fecha de publicaci�n podemos continuar editando
	 * Assert.isTrue(trip.getPublicationDate() == null);
	 * //Comprobamos que el trip aun no ha empezado
	 * Assert.isTrue(trip.getStartDate().after(currentDate));
	 * boolean isManager = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER);
	 * boolean isExplorer = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.EXPLORER);
	 * 
	 * if (isManager) {
	 * //Saco los trips del manager
	 * Collection<Trip> trips = this.findAllTripsByManagerId(this.managerService.findByPrincipal().getId());
	 * //Compruebo que ese trip que quiera editar lo cre� �l
	 * Assert.isTrue(trips.contains(trip));
	 * trip.setCancelled(true);
	 * } else if (isExplorer) {
	 * //Saco los trips del explorer con estado ACCEPTED
	 * Collection<Trip> trips = this.findAllTripsByExplorerIdWithStatusAccepted(this.explorerService.findByPrincipal().getId());
	 * //Compruebo que ese trip que quiera editar lo organiz� �l
	 * Assert.isTrue(trips.contains(trip));
	 * trip.setCancelled(true);
	 * }
	 * Trip result;
	 * result = this.tripRepository.save(trip);
	 * return result;
	 * }
	 */

	public Collection<Trip> findAll() {
		Collection<Trip> result = new ArrayList<Trip>();
		Assert.notNull(this.tripRepository);
		result = this.tripRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Trip findOne(final int tripId) {
		Trip result;
		result = this.tripRepository.findOne(tripId);
		return result;
	}

	public void delete(final Trip trip) {
		assert trip != null;
		assert trip.getId() != 0;
		//Comprobamos que ese trip no tenga fecha de publicaci�n
		Assert.isTrue(trip.getPublicationDate() == null);
		//Comprobams que ese Trip sea de ese manager
		this.checkPrincipalManager(this.tripRepository.findOne(trip.getId()));
		//Borramos el trip
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
		res = this.tripRepository.findAllTripsByManagerId(managerId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Trip> findAllTripsByExplorerIdWithStatusAccepted(int explorerId) {
		//Assert.notNull(explorerId);
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.findAllTripsByExplorerIdWithStatusAccepted(explorerId);
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

	//Para sacar los trips con estado ACCEPTED para a laz hora de borrarlos un explorer
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

	//Requisito 12.2
	public Trip findTripWithStatusPendingById(int tripId) {
		Trip result = this.tripRepository.findTripWithStatusPendingById(tripId);
		Assert.notNull(result);
		return result;
	}

	//Editar un Trip para un Manager
	public Trip findOneToEditManager(final int tripId) {
		Trip trip;
		Trip tripEdit;
		//Trip a editar
		trip = this.tripRepository.findOne(tripId);
		//Para que un manager edite un trip NO puede tener publicationDate
		assert trip.getPublicationDate() != null;
		//Comprobamos que sea de ese Manager
		this.checkPrincipalManager(this.tripRepository.findOne(tripId));
		tripEdit = this.tripRepository.save(trip);
		return tripEdit;
	}

	//Editar un Trip para un Explorer
	public Trip findOneToEditExplorer(final int tripId) {
		Trip trip;
		Trip tripEdit;
		Collection<Trip> tripsAccepted = new ArrayList<Trip>();
		tripsAccepted = this.findTripsWhitStatusAccepted();
		//Trip a editar
		trip = this.tripRepository.findOne(tripId);
		//Para que un explorer edite un trip NO puede tener publicationDate
		assert trip.getPublicationDate() != null;
		//Para que un explorer edite un trip debe de tener el estatus ACCEPTED
		Assert.isTrue(!tripsAccepted.contains(trip));
		//Comprobamos que sea de ese Manager
		this.checkPrincipalExplorer(this.tripRepository.findOne(tripId));
		tripEdit = this.tripRepository.save(trip);
		return tripEdit;
	}

	//Todos los Trips que apply un explorer
	public Collection<Trip> findAllTripsApplyByExplorerId(int explorerId) {
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findAllTripsApplyByExplorerId(explorerId));
		Assert.notNull(trips);
		return trips;
	}

	//Trips auditados por el auditorId
	public Collection<Trip> findByAuditorId(int auditorId) {
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findByAuditorId(auditorId));
		Assert.notNull(trips);
		return trips;

	}

	//Para el checkPrincipalManager obtenemos el explorer.
	public Explorer findExplorerByTripId(int tripId) {
		Explorer explorer;
		explorer = this.tripRepository.findExplorerByTripId(tripId);
		Assert.notNull(explorer);
		return explorer;
	}

	//**********************************************************************
	//SACAMOS LOS TRIPS DEL MANAGER QUE EST� CONECTADO
	public Collection<Trip> findByPrincipal() {

		final Manager manager = this.managerService.findByPrincipal();

		Collection<Trip> result;

		result = this.tripRepository.findAllTripsByManagerId(manager.getId());

		return result;
	}

	//COMPROBAMOS QUE ESE TRIP SEA DEL MANAGER QUE EST� CONECTADO
	public void checkPrincipalManager(final Trip trip) {
		Assert.notNull(trip);
		final Manager m = trip.getManager();
		final Manager first = this.managerService.findByPrincipal();
		Assert.isTrue(m.equals(first));
	}

	//COMPROBAMOS QUE ESE TRIP SEA DEL EXPLORER QUE EST� CONECTADO
	public void checkPrincipalExplorer(final Trip trip) {
		Assert.notNull(trip);
		final Explorer e = this.findExplorerByTripId(trip.getId());
		final Explorer first = this.explorerService.findByPrincipal();
		Assert.isTrue(e.equals(first));
	}
}
