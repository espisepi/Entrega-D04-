
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Attachment;
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
	private TripService		tripService;


	@Test
	public void testCreatePositive() {
		super.authenticate("explorer1");
		Story story;
		story = this.storyService.create();
		Assert.notNull(story);
		Assert.notNull(story.getExplorer());
		super.unauthenticate();
	}

	@Test
	public void testSavePositive() {
		super.authenticate("explorer1");
		Story story;
		final Attachment attachment1 = new Attachment();
		story = this.storyService.create();
		Trip trip1;

		final Collection<Attachment> attachments = new ArrayList<Attachment>();

		attachment1.setUrl("http://www.testStory.com");
		attachments.add(attachment1);
		trip1 = this.tripService.findOne(super.getEntityId("trip1"));

		story.setTitle("title story test");
		story.setText("text story test");
		story.setAttachments(attachments);
		story.setTrip(trip1);
		Assert.notNull(story.getExplorer());

		story = this.storyService.save(story);
		Assert.notNull(story.getId());
		//Compruebo que tiene esta Story el explorerPrincipal y la trip1 desde la bd
		//Para que funcione el siguiente codigo cambiar el metodo save a saveAndFlush de storyRepository en storyService
		//El codigo solo se comenta en las relaciones bidireccionales porque tiene que guardarse en la bd primero para que se actualice sus objetos relacionados
		//		Assert.isTrue(story.getExplorer().getStories().contains(story));
		//		Assert.isTrue(trip1.getStories().contains(story));

		super.unauthenticate();
	}

	@Test
	public void testFindAllPositive() {
		Collection<Story> storys;
		storys = this.storyService.findAll();
		Assert.notEmpty(storys);
	}

	@Test
	public void testFindOnePositive() {
		Story story;
		story = this.storyService.findOne(super.getEntityId("story1"));
		Assert.notNull(story);
	}

	@Test
	public void testDelete() {
		Story story;

		story = this.storyService.findOne(super.getEntityId("story1"));

		this.storyService.delete(story);
	}
}
