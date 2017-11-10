
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
import domain.Category;
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
		//DEVUELVEME EL ACTOR QUE ESTÁ AUTENTICADO
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
	@Rollback(false)
	public void testSave() {
		this.authenticate("manager1");
		Trip tripSaved;
		Trip trip;
		Manager manager;
		Ranger ranger;
		LegalText legalText;
		Collection<Stage> stages;
		String ticker;
		String title;
		String description;
		Collection<String> requerimentsExplorers;
		Date startDate;
		Date finishDate;
		Boolean cancelled;
		Stage stage;
		double price;
		Category category;

		category = this.categoryService.findOne(super.getEntityId("water"));
		legalText = this.legalTextService.findOne(super.getEntityId("legalText1"));
		stage = this.stageService.create();
		stage.setTitle("title test");
		stage.setDescription("description test");
		stage.setTrip(this.tripService.findOne(super.getEntityId("trip1")));
		stage = this.stageService.save(stage);

		Calendar calendar1 = new GregorianCalendar();
		calendar1.set(2018, 0, 31, 12, 5, 0);
		final Date fecha1 = calendar1.getTime();

		final Calendar calendar2 = new GregorianCalendar();
		calendar2.set(2015, 0, 31, 12, 5, 0);
		final Date fecha2 = calendar2.getTime();

		manager = this.managerService.findByPrincipal();
		ranger = this.rangerService.findOne(super.getEntityId("ranger1"));
		trip = this.tripService.create(manager);
		ticker = "170412-WWWW";
		title = "trip1";
		description = "trip de test";
		requerimentsExplorers = new ArrayList<String>();
		cancelled = false;
		stages = new ArrayList<Stage>();
		stages.add(stage);
		price = 400.;

		trip.setRanger(ranger);
		trip.setTicker(ticker);
		trip.setTitle(title);
		trip.setDescription(description);
		trip.setRequirementsExplorers(requerimentsExplorers);
		trip.setStartDate(fecha2);
		trip.setFinishDate(fecha1);
		trip.setCancelled(cancelled);
		trip.getStages().add(stage);
		trip.setPrice(price);
		trip.setLegalText(legalText);
		trip.getCategories().add(category);

		tripSaved = this.tripService.save(trip);
		//Assert.notNull(tripSaved);
	}
	//	@Test
	//	public void testSave() {
	//
	//		final Manager manager = this.managerService.create();
	//		final Trip trip = this.tripService.create(manager);
	//		trip.setTicker("170112-WWWW");
	//		trip.setTitle("title 1");
	//		trip.setDescription("description 1");
	//		final List<String> requeriments = new ArrayList<>();
	//		trip.setRequirementsExplorers(requeriments);
	//
	//		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:ii");
	//		final String stringPublicationDate = "2017/09/15 22:45";
	//		final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
	//		final String stringStartDate = "2017/02/03";
	//		final String stringFinishtDate = "2017/02/04";
	//
	//		trip.setReasonWhy("reasonWhy 1");
	//		trip.setCancelled(false);
	//
	//		final Ranger ranger = this.rangerService.create();
	//
	//		final SurvivalClass classes = this.survivalClassService.create();
	//		final Story stories = this.storyService.create();
	//		final ApplicationFor applicationFors = this.applicationForService.create();
	//		final AuditRecord auditRecords = this.auditRecordService.create();
	//		final Note notes = this.noteService.create();
	//		final Category categories = this.categoryService.create();
	//		final Sponsorship sponsorships = this.sponsorshipService.create();
	//		final Stage stages = this.stageService.create();
	//		final Tag tags = this.tagService.create();
	//
	//	}

}
