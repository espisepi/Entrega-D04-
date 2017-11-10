
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TripServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private TripService	tripService;


	//	// Supporting services ----------------------------------------------------
	//	@Autowired
	//	private RangerService			rangerService;
	//	@Autowired
	//	private ManagerService			managerService;
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
	//	@Autowired
	//	private CategoryService			categoryService;
	//	@Autowired
	//	private SponsorshipService		sponsorshipService;
	//	@Autowired
	//	private StageService			stageService;
	//	@Autowired
	//	private TagService				tagService;

	@Test
	public void testCreatePositive() {
		//		super.authenticate("manager1");
		//		Manager managerPrincipal;
		//		Trip trip;
		//
		//		managerPrincipal = this.managerService.findByPrincipal();
		//		trip = this.tripService.create(managerPrincipal);
		//		Assert.notNull(trip);
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
