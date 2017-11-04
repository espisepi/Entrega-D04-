
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ApplicationFor;
import domain.Explorer;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationForServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private ApplicationForService	applicationForService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ExplorerService			explorerService;
	@Autowired
	private TripService				tripService;


	@Test
	public void testCreatePositive() {
		ApplicationFor applicationFor;
		applicationFor = this.applicationForService.create();
		Assert.notNull(applicationFor);
		Assert.isTrue(applicationFor.getStatus() == "PENDING");
	}

	@Test
	public void testSavePositive() {
		ApplicationFor applicationFor;

		applicationFor = this.applicationForService.create();

		final List<Explorer> explorers = new ArrayList<Explorer>(this.explorerService.findAll());
		final List<Trip> trips = new ArrayList<Trip>(this.tripService.findAll());
		final List<ApplicationFor> applicationsFor = new ArrayList<ApplicationFor>(this.applicationForService.findAll());

		applicationFor.setMoment(new Date());
		applicationFor.setExplorer(explorers.get(0));
		applicationFor.setCreditCard(applicationsFor.get(0).getCreditCard());
		applicationFor.setTrip(trips.get(0));

		applicationFor = this.applicationForService.save(applicationFor);
		Assert.notNull(applicationFor.getId());

	}
	@Test
	public void testFindAllPositive() {
		Collection<ApplicationFor> applicationFors;
		applicationFors = this.applicationForService.findAll();
		Assert.notEmpty(applicationFors);
	}

	//	@Test
	//	public void testFindOnePositive() {
	//		ApplicationFor applicationFor;
	//		applicationFor = this.applicationForService.findOne(6248);
	//		Assert.notNull(applicationFor);
	//	}
}
