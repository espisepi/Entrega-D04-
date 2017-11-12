
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Auditor;
import domain.Category;
import domain.Explorer;
import domain.LegalText;
import domain.Manager;
import domain.Ranger;
import domain.Stage;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TripServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private TripService			tripService;

	//	// Supporting services ----------------------------------------------------
	@Autowired
	private RangerService		rangerService;
	@Autowired
	private ManagerService		managerService;
	@Autowired
	private ExplorerService		explorerService;
	@Autowired
	private AuditorService		auditorService;

	//	@Autowired
	//	private SurvivalClassService	survivalClassService;
	//	@Autowired
	//	private StoryService			storyService;
	//	@Autowired
	//	private ApplicationForService	applicationForService;
	//	@Autowired
	//	private AuditRecordService		auditRecordService;
	//	@Autowired
	//	private NoteService				noteService;
	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private StageService		stageService;

	@Autowired
	private LegalTextService	legalTextService;


	//	@Autowired
	//	private TagService				tagService;

	@Test
	public void testCreate() {
		this.authenticate("manager1");
		Trip result;
		Manager manager;
		//DEVUELVEME EL ACTOR QUE EST� AUTENTICADO
		manager = this.managerService.findByPrincipal();
		result = this.tripService.create(manager);
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Trip> result = this.tripService.findAll();
		Assert.notEmpty(result);
	}

	@Test
	public void testSave() {
		this.authenticate("manager1");
		Trip tripSaved;
		Trip trip;
		Manager manager;
		Ranger ranger;
		LegalText legalText;
		String ticker;
		String title;
		String description;
		Collection<String> requerimentsExplorers;
		Boolean cancelled;
		Stage stage;
		double price;
		Category category;
		Date startDate;
		Date finishDate;
		Calendar calendar1;
		Calendar calendar2;

		calendar1 = new GregorianCalendar();
		calendar1.set(2018, 0, 31, 12, 5, 0);
		startDate = calendar1.getTime();

		calendar2 = new GregorianCalendar();
		calendar2.set(2015, 0, 31, 12, 5, 0);
		finishDate = calendar2.getTime();

		category = this.categoryService.findOne(super.getEntityId("water"));
		legalText = this.legalTextService.findOne(super.getEntityId("legalText1"));
		manager = this.managerService.findByPrincipal();
		ranger = this.rangerService.findOne(super.getEntityId("ranger1"));
		trip = this.tripService.create(manager);
		ticker = "170412-WWWW";
		title = "trip1";
		description = "trip de test";
		requerimentsExplorers = new ArrayList<String>();
		cancelled = false;
		price = 400.;

		trip.setRanger(ranger);
		trip.setTicker(ticker);
		trip.setTitle(title);
		trip.setDescription(description);
		trip.setRequirementsExplorers(requerimentsExplorers);
		trip.setStartDate(startDate);
		trip.setFinishDate(finishDate);
		trip.setCancelled(cancelled);
		trip.setPrice(price);
		trip.setLegalText(legalText);
		trip.getCategories().add(category);
		tripSaved = this.tripService.save(trip);

		stage = this.stageService.create();
		stage.setTitle("title test");
		stage.setDescription("description test");
		stage.setPrice(33.);
		stage.setNumber(4);
		stage.setTrip(tripSaved);
		stage = this.stageService.save(stage);

		this.authenticate(null);
	}
	@Test
	public void testFindOne() {
		Trip trip;

		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
	}

	@Test
	public void testDelete() {
		this.authenticate("manager1");
		Trip tripSaved;
		Trip trip;
		Manager manager;
		Ranger ranger;
		LegalText legalText;
		String ticker;
		String title;
		String description;
		Collection<String> requerimentsExplorers;
		Boolean cancelled;
		Stage stage;
		double price;
		Category category;
		Date startDate;
		Date finishDate;
		Calendar calendar1;
		Calendar calendar2;

		calendar1 = new GregorianCalendar();
		calendar1.set(2018, 0, 31, 12, 5, 0);
		startDate = calendar1.getTime();

		calendar2 = new GregorianCalendar();
		calendar2.set(2015, 0, 31, 12, 5, 0);
		finishDate = calendar2.getTime();

		category = this.categoryService.findOne(super.getEntityId("water"));
		legalText = this.legalTextService.findOne(super.getEntityId("legalText1"));
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		ranger = this.rangerService.findOne(super.getEntityId("ranger1"));
		trip = this.tripService.create(manager);
		ticker = "170412-WWWW";
		title = "trip1";
		description = "trip de test";
		requerimentsExplorers = new ArrayList<String>();
		cancelled = false;
		price = 400.;

		trip.setRanger(ranger);
		trip.setTicker(ticker);
		trip.setTitle(title);
		trip.setDescription(description);
		trip.setRequirementsExplorers(requerimentsExplorers);
		trip.setStartDate(startDate);
		trip.setFinishDate(finishDate);
		trip.setCancelled(cancelled);
		trip.setPrice(price);
		trip.setLegalText(legalText);
		trip.getCategories().add(category);
		tripSaved = this.tripService.save(trip);

		stage = this.stageService.create();
		stage.setTitle("title test");
		stage.setDescription("description test");
		stage.setPrice(33.);
		stage.setNumber(4);
		stage.setTrip(tripSaved);
		stage = this.stageService.save(stage);
		manager.getTrips().add(tripSaved);

		this.tripService.delete(tripSaved);
		Assert.isNull(this.tripService.findOne(tripSaved.getId()));
		this.authenticate(null);
	}

	// Other business test methods -------------------------------------------------
	@Test
	public void testFindAllTripsNoAuthenticate() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>(this.tripService.findAllTripsNoAuthenticate());
		Assert.notNull(trips);
	}

	@Test
	public void testFindAllTripsPublishedNotStarted() {
		this.authenticate("manager1");
		Collection<Trip> trips;
		trips = new ArrayList<Trip>(this.tripService.findAllTripsPublishedNotStarted());
		Assert.notNull(trips);
		this.authenticate(null);
	}

	@Test
	public void testFindTripsWhitStatusAccepted() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>(this.tripService.findTripsWhitStatusAccepted());
		Assert.notNull(trips);
	}

	@Test
	@Rollback(false)
	public void testFindOneToEditManager() {
		this.authenticate("manager1");
		Trip trip;
		Trip tripEdit;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		//trip.setDescription("ESTA");
		trip.setCancelled(true);
		trip.setReasonWhy("PORQUE SI");
		tripEdit = this.tripService.findOneToEdit(trip.getId());
		Assert.notNull(tripEdit);
		this.authenticate(null);
	}

	@Test
	@Rollback(false)
	public void testFindOneToEditExplorer() {
		this.authenticate("explorer3");
		Trip trip;
		Trip tripEdit;
		trip = this.tripService.findOne(super.getEntityId("trip3"));
		trip.setCancelled(true);
		trip.setReasonWhy("No hay fondos");
		tripEdit = this.tripService.findOneToEdit(trip.getId());
		Assert.notNull(tripEdit);
		this.authenticate(null);
	}

	@Test
	public void testFindAllTripsApplyByExplorerId() {
		Collection<Trip> trips;
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		trips = new ArrayList<Trip>(this.tripService.findAllTripsApplyByExplorerId(explorer.getId()));
		Assert.notNull(trips);
	}

	@Test
	public void testFindByAuditorId() {
		Collection<Trip> trips;
		Auditor auditor;
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		trips = new ArrayList<Trip>(this.tripService.findByAuditorId(auditor.getId()));
		Assert.notNull(trips);
	}

}
