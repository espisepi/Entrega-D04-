
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Stage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class StageServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private StageService	stageService;

	// Supporting services ----------------------------------------------------
	@Autowired
	private TripService		tripService;


	@Test
	public void testCreate() {
		Stage stage;

		stage = this.stageService.create();
		Assert.notNull(stage);

	}

	@Test
	public void testSave() {
		Stage stage;

		stage = this.stageService.create();
		stage.setTitle("title test");
		stage.setDescription("description test");
		stage.setTrip(this.tripService.findOne(super.getEntityId("trip1")));
		stage = this.stageService.save(stage);
		Assert.isTrue(stage.getId() != 0);
		Assert.isTrue((this.tripService.findOne(super.getEntityId("trip1"))).getStages().contains(stage));
	}
}
