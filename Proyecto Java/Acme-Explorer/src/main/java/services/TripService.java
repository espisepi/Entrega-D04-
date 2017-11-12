
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
	//***** TEST HECHO *******
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

	//***** TEST HECHO *******
	public Trip save(final Trip trip) {
		assert trip != null;
		Trip result;
		if (trip.isCancelled())
			Assert.notNull(trip.getReasonWhy());
		//Sólo los legalText que estén guardados como draftMode pueden ser referenciados a una Trip.
		Assert.isTrue(trip.getLegalText().isDraftMode());
		result = this.tripRepository.save(trip);
		return result;
	}

	/*
	 * !!!!!!! UPDATE HECHO CON SET !!!!!
	 * public Trip update(Trip trip) {
	 * assert trip != null;
	 * Date currentDate = new Date();
	 * //Si no tiene fecha de publicación podemos continuar editando
	 * Assert.isTrue(trip.getPublicationDate() == null);
	 * //Comprobamos que el trip aun no ha empezado
	 * Assert.isTrue(trip.getStartDate().after(currentDate));
	 * boolean isManager = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER);
	 * boolean isExplorer = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.EXPLORER);
	 * 
	 * if (isManager) {
	 * //Saco los trips del manager
	 * Collection<Trip> trips = this.findAllTripsByManagerId(this.managerService.findByPrincipal().getId());
	 * //Compruebo que ese trip que quiera editar lo creó él
	 * Assert.isTrue(trips.contains(trip));
	 * trip.setCancelled(true);
	 * } else if (isExplorer) {
	 * //Saco los trips del explorer con estado ACCEPTED
	 * Collection<Trip> trips = this.findAllTripsByExplorerIdWithStatusAccepted(this.explorerService.findByPrincipal().getId());
	 * //Compruebo que ese trip que quiera editar lo organizó él
	 * Assert.isTrue(trips.contains(trip));
	 * trip.setCancelled(true);
	 * }
	 * Trip result;
	 * result = this.tripRepository.save(trip);
	 * return result;
	 * }
	 */

	//***** TEST HECHO *******
	public Collection<Trip> findAll() {
		Collection<Trip> result = new ArrayList<Trip>();
		Assert.notNull(this.tripRepository);
		result = this.tripRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	//***** TEST HECHO *******
	public Trip findOne(final int tripId) {
		Trip result;
		result = this.tripRepository.findOne(tripId);
		return result;
	}

	//***** TEST HECHO *******
	public void delete(final Trip trip) {
		assert trip != null;
		assert trip.getId() != 0;
		//Comprobamos que ese trip no tenga fecha de publicación
		Assert.isNull(trip.getPublicationDate());
		Manager manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager.getTrips().contains(trip));
		this.tripRepository.delete(trip);
	}

	// Other business methods -------------------------------------------------
	//***** TEST HECHO *******
	//Para quien no esté autenticado devolvemos todos los trips con restricciones
	public Collection<Trip> findAllTripsNoAuthenticate() {
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	//***** TEST HECHO *******
	//Requisito 12.3
	public Collection<Trip> findAllTripsPublishedNotStarted() {
		Collection<Trip> trips = new ArrayList<>();
		Collection<Trip> res = new ArrayList<>();
		Date currentDate = new Date();
		this.managerService.checkPrincipal();
		//Assert.isTrue(!this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER));
		trips.addAll(this.tripRepository.findAllTripsPublished());

		for (Trip t : trips)
			if (t.getStartDate().after(currentDate) == true)
				res.add(t);
		Assert.notNull(res);
		return res;
	}

	//***** TEST HECHO *******
	//Para sacar los trips con estado ACCEPTED para que un explorer pueda cancelarlo
	public Collection<Trip> findTripsWhitStatusAccepted() {
		Collection<Trip> trips = new ArrayList<>();
		Collection<Trip> res = new ArrayList<>();
		Date currentDate = new Date();
		//Assert.isTrue(this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.EXPLORER));
		trips.addAll(this.tripRepository.findTripsWhitStatusAccepted());
		Assert.notNull(res);
		for (Trip t : trips)
			if (t.getStartDate().after(currentDate) == true)
				res.add(t);
		return res;
	}

	//**********************************************************************************
	//***********************  METODO EDITAR  ******************************************
	//**********************************************************************************
	public Trip findOneToEdit(final int tripId) {
		Trip trip;
		Trip tripEdit;
		Manager manager;
		//Trip a editar
		trip = this.tripRepository.findOne(tripId);
		//Para que un manager edite un trip NO puede tener publicationDate
		//salta si tiene fecha
		Assert.isNull(trip.getPublicationDate());
		//Comprobamos que sea de ese Manager
		manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager.getTrips().contains(trip));
		//Lo editamos
		tripEdit = this.tripRepository.save(trip);
		return tripEdit;
	}

	//**********************************************************************************
	//***********************  METODO CANCELAR  ****************************************
	//**********************************************************************************
	public Trip findOneToCancel(final int tripId) {
		Date date;
		Trip trip;
		Trip tripEdit;
		boolean isManager;
		boolean isExplorer;
		Manager manager;
		Explorer explorer;
		Collection<Trip> tripsAccepted;
		Collection<Explorer> explorers;

		//Trip a editar
		trip = this.tripRepository.findOne(tripId);
		//No tenemos que restringir por roles pero para que un explorer edite un trip debe estar en accepted
		//Requisito 12.3 para manager
		//Requisito 13.4 para explorer

		//isManager = this.managerService.findByPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER);
		isManager = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.MANAGER);
		isExplorer = this.actorService.findPrincipal().getUserAccount().getAuthorities().contains(Authority.EXPLORER);
		date = new Date();
		//Para que un manager cancele un trip NO puede haber empezado
		//Para que un explorer cancele un trip NO puede haber empezado
		Assert.isTrue(trip.getStartDate().before(date));
		if (isManager) {
			//Comprobamos que sea de ese Manager
			manager = this.managerService.findByPrincipal();
			Assert.isTrue(manager.getTrips().contains(trip));
			//Para que un manager edite un trip puede tener publicationDate
			Assert.notNull(trip.getPublicationDate());

		} else if (isExplorer) {
			tripsAccepted = new ArrayList<Trip>(this.findTripsWhitStatusAccepted());
			assert trip.getPublicationDate() != null;
			//Para que un explorer edite un trip debe de tener el estatus ACCEPTED
			Assert.isTrue(tripsAccepted.contains(trip));
			//Comprobamos que sea de ese Explorer
			//explorer conectado
			explorer = this.explorerService.findByPrincipal();
			//Lista de explorer con ese trip
			explorers = new ArrayList<Explorer>(this.tripRepository.findExplorersByTripId(tripId));
			//Vemos si el explorer conectado tiene ese trip
			Assert.isTrue(explorers.contains(explorer));
		}
		tripEdit = this.tripRepository.save(trip);
		return tripEdit;
	}
	//Todos los Trips que apply un explorer
	//***** TEST HECHO *******
	public Collection<Trip> findAllTripsApplyByExplorerId(int explorerId) {
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findAllTripsApplyByExplorerId(explorerId));
		Assert.notNull(trips);
		return trips;
	}

	//Trips auditados por el auditorId
	//***** TEST HECHO *******
	public Collection<Trip> findByAuditorId(int auditorId) {
		Collection<Trip> trips = new ArrayList<Trip>();
		trips.addAll(this.tripRepository.findByAuditorId(auditorId));
		Assert.notNull(trips);
		return trips;

	}

	//Para el checkPrincipalExplorer obtenemos el explorer.
	//***** TEST HECHO *******
	public Collection<Explorer> findExplorersByTripId(int tripId) {
		Collection<Explorer> explorers;
		explorers = new ArrayList<Explorer>(this.tripRepository.findExplorersByTripId(tripId));
		Assert.notNull(explorers);
		return explorers;
	}

}
