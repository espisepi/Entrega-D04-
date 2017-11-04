
package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ApplicationFor;
import domain.AuditRecord;
import domain.Category;
import domain.Manager;
import domain.Note;
import domain.Ranger;
import domain.Sponsorship;
import domain.Stage;
import domain.Story;
import domain.Tag;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TripServiceTest {

	//Service under test----------------------------------------------------------
	private TripService				tripService;

	// Supporting services ----------------------------------------------------
	private RangerService			rangerService;
	private ManagerService			managerService;
	private SurvivalClassService	survivalClassService;
	private StoryService			storyService;
	private ApplicationForService	applicationForService;
	private AuditRecordService		auditRecordService;
	private NoteService				noteService;
	private CategoryService			categoryService;
	private SponsorshipService		sponsorshipService;
	private StageService			stageService;
	private TagService				tagService;


	@Test
	public void testCreatePositive() {
		Trip trip;
		trip = this.tripService.create();
		Assert.notNull(trip);
	}

	@Test
	public void testSavePositive() {
		Trip trip = this.tripService.create();
		trip.setTicker("170112-WWWW");
		trip.setTitle("title 1");
		trip.setDescription("description 1");
		List<String> requeriments = new ArrayList<>();
		trip.setRequirementsExplorers(requeriments);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:ii");
		String stringPublicationDate = "2017/09/15 22:45";
		//Date publicationDate = sdf1.parse(stringPublicationDate);
		//trip.setPublicationDate(publicationDate);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		String stringStartDate = "2017/02/03";
		//Date startDate = sdf2.parse(stringStartDate);
		//trip.setStartDate(startDate);
		String stringFinishtDate = "2017/02/04";
		//Date finishDate = sdf2.parse(stringFinishtDate);
		//trip.setFinishDate(finishDate);
		trip.setReasonWhy("reasonWhy 1");
		trip.setCancelled(false);

		Ranger ranger = this.rangerService.create();
		Manager manager = this.managerService.create();
		//SurvivalClass classes = this.survivalClassService.create();
		Story stories = this.storyService.create();
		ApplicationFor applicationFors = this.applicationForService.create();
		AuditRecord auditRecords = this.auditRecordService.create();
		Note notes = this.noteService.create();
		Category categories = this.categoryService.create();
		Sponsorship sponsorships = this.sponsorshipService.create();
		Stage stages = this.stageService.create();
		Tag tags = this.tagService.create();
	}

}
