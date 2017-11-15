
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
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
		trip.setTicker(this.generatedTicker());

		return trip;
	}

	//***** TEST HECHO *******
	public Trip save(final Trip trip) {
		assert trip != null;
		if (trip.getId() == 0)
			trip.setTicker(this.generatedTicker());
		Trip result;
		//Sólo los legalText que estén guardados como draftMode pueden ser referenciados a una Trip.
		Assert.isTrue(!trip.getLegalText().isDraftMode());
		result = this.tripRepository.save(trip);
		return result;
	}

	//***** TEST HECHO *******
	public Collection<Trip> findAll() {
		Collection<Trip> result;
		Assert.notNull(this.tripRepository);
		result = new ArrayList<Trip>(this.tripRepository.findAll());
		Assert.notNull(result);
		return result;
	}

	//***** TEST HECHO *******
	public Trip findOne(final int tripId) {
		Trip result;
		Assert.isTrue(tripId != 0);
		result = this.tripRepository.findOne(tripId);
		return result;
	}

	//***** TEST HECHO *******
	public void delete(final Trip trip) {
		Manager manager;
		assert trip != null;
		assert trip.getId() != 0;
		Collection<Trip> tripsWithoutPublicationDate;
		//Obtenemos todos los trips que no tengan Publication Date.
		tripsWithoutPublicationDate = new ArrayList<Trip>(this.tripRepository.findAllTripsNotPublished());
		//Comprobamos que ese trip no tenga fecha de publicación
		Assert.isTrue(tripsWithoutPublicationDate.contains(trip));
		manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager.getTrips().contains(trip));
		this.tripRepository.delete(trip);
	}

	// Other business methods -------------------------------------------------
	//***** TEST HECHO *******
	//Para quien no esté autenticado devolvemos todos los trips con restricciones
	public Collection<Trip> findAllTripsNoAuthenticate() {
		Collection<Trip> res;
		res = new ArrayList<Trip>(this.tripRepository.findAllTripsNoAuthenticate());
		Assert.notNull(res);
		return res;
	}

	//***** TEST HECHO *******
	//Requisito 12.3
	public Collection<Trip> findAllTripsPublishedNotStarted() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>(this.tripRepository.findAllTripsPublishedNotStarted());
		Assert.notNull(trips);
		return trips;
	}

	//***** TEST HECHO *******
	//Para sacar los trips con estado ACCEPTED para que un explorer pueda cancelarlo
	public Collection<Trip> findTripsWhitStatusAcceptedNotStarted() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>(this.tripRepository.findTripsWhitStatusAcceptedNotStarted());
		Assert.notNull(trips);
		return trips;
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
	public Trip findOneToCancelManager(final int tripId) {
		this.managerService.checkPrincipal();
		Collection<Trip> allTrips;
		Trip trip;
		Trip tripEdit;
		Manager manager;
		//Trip a editar
		trip = this.tripRepository.findOne(tripId);
		//Para que un manager cancele un trip NO puede haber empezado
		allTrips = new ArrayList<>(this.tripRepository.findAllTripsPublishedNotStarted());
		Assert.isTrue(allTrips.contains(trip));
		//Comprobamos que sea de ese Manager
		manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager.getTrips().contains(trip));
		tripEdit = this.tripRepository.save(trip);
		if (trip.isCancelled())
			Assert.notNull(trip.getReasonWhy());
		return tripEdit;
	}

	public Trip findOneToCancelExplorer(final int tripId) {
		this.explorerService.checkPrincipal();
		Trip trip;
		Trip tripEdit;
		Explorer explorer;
		Collection<Trip> tripsAccepted;
		Collection<Explorer> explorers;

		//Trip a editar
		trip = this.tripRepository.findOne(tripId);
		//Para que un explorer edite un trip debe de tener el estatus ACCEPTED
		tripsAccepted = new ArrayList<Trip>(this.findTripsWhitStatusAcceptedNotStarted());
		//Comprobamos que sea de ese Explorer
		Assert.isTrue(tripsAccepted.contains(trip));
		//explorer conectado
		explorer = this.explorerService.findByPrincipal();
		//Lista de explorer con ese trip
		explorers = new ArrayList<Explorer>(this.explorerService.findExplorersByTripId(tripId));
		//Vemos si el explorer conectado tiene ese trip
		Assert.isTrue(explorers.contains(explorer));
		tripEdit = this.tripRepository.save(trip);
		if (trip.isCancelled())
			Assert.notNull(trip.getReasonWhy());
		return tripEdit;
	}

	//Todos los Trips que apply un explorer
	//***** TEST HECHO *******
	public Collection<Trip> findAllTripsApplyByExplorerId(int explorerId) {
		Collection<Trip> trips;
		trips = new ArrayList<>(this.tripRepository.findAllTripsApplyByExplorerId(explorerId));
		Assert.notNull(trips);
		return trips;
	}

	//Trips auditados por el auditorId
	//***** TEST HECHO *******
	public Collection<Trip> findByAuditorId(int auditorId) {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>(this.tripRepository.findByAuditorId(auditorId));
		Assert.notNull(trips);
		return trips;

	}

	public Collection<Trip> findAllTripsNotPublished() {
		Collection<Trip> trips;
		trips = new ArrayList<>();
		Assert.notNull(trips);
		return trips;
	}

	public Collection<Trip> findAllTripsByCategoryId(final int categoryId) {
		Collection<Trip> res;
		res = this.tripRepository.findAllTripsByCategoryId(categoryId);
		//Me da igual si el valor es null, no quiero que salte una excepción
		return res;
	}

	//	public void setPriceOfTrip(Trip trip) {
	//		Collection<Stage> stagesOfTrip;
	//		Double priceOfTrip;
	//
	//		priceOfTrip = 0.0;
	//		stagesOfTrip = this.findOne(trip.getId()).getStages();
	//
	//		for (Stage s : stagesOfTrip)
	//			priceOfTrip = priceOfTrip + s.getTotalPrice();
	//		trip.setPrice(priceOfTrip);
	//	}

	public Double findPrice(int tripId) {
		Double price;

		price = this.tripRepository.findPrice(tripId);
		Assert.notNull(price);

		return price;
	}
	public void setPriceOfTrip(Trip trip) {
		Double price;
		price = this.tripRepository.findPrice(trip.getId());
		trip.setPrice(price);

	}

	public void setPriceOfAllTrips() {
		Collection<Trip> trips;
		trips = this.tripRepository.findAll();

		for (Trip t : trips)
			this.setPriceOfTrip(t);

	}

	public String generatedTicker() {

		Calendar calendar;

		calendar = Calendar.getInstance();
		String ticker;

		ticker = String.valueOf(calendar.get(Calendar.YEAR)).substring(2) + String.valueOf(calendar.get(Calendar.MONTH) + 1) + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		char[] arr = new char[] {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};
		String cadenaAleatoria = "";
		for (Integer i = 0; i <= 3; i++) {
			char elegido = arr[(int) (Math.random() * 26)];
			cadenaAleatoria = cadenaAleatoria + elegido;

		}

		ticker = ticker + "-" + cadenaAleatoria;

		return ticker;
	}

}
