
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Attachment;
import domain.Explorer;
import domain.Story;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class StoryServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private StoryService	storyService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ExplorerService	explorerService;
	@Autowired
	private TripService		tripService;


	@Test
	public void testCreatePositive() {
		Story story;
		story = this.storyService.create();
		Assert.notNull(story);
	}

	@Test
	public void testSavePositive() {
		Story story;
		story = this.storyService.create();

		final List<Explorer> explorers = new ArrayList<Explorer>(this.explorerService.findAll());
		final List<Trip> trips = new ArrayList<Trip>(this.tripService.findAll());
		final Collection<Attachment> attachments = new ArrayList<Attachment>();
		//attachments.add((new Attachment()).setUrl("attachment1Test"));

		story.setTitle("title story test");
		story.setText("text story test");
		story.setAttachments(attachments);
		story.setExplorer(explorers.get(0));
		story.setTrip(trips.get(0));
		Assert.notNull(story.getExplorer());

		story = this.storyService.save(story);
		Assert.notNull(story.getId());

	}
	@Test
	public void testFindAllPositive() {
		Collection<Story> storys;
		storys = this.storyService.findAll();
		Assert.notEmpty(storys);
	}

	//	@Test
	//	public void testFindOnePositive() {
	//		Story story;
	//		story = this.storyService.findOne(6248);
	//		Assert.notNull(story);
	//	}
}
