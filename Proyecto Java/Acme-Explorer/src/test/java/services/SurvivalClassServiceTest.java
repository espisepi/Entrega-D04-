
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.GPS;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SurvivalClassServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private SurvivalClassService	survivalClassService;

	@Autowired
	private ManagerService			managerService;


	// Tests ----------------------------------------------

	@Test
	public void createTest() {
		this.authenticate("manager1");
		SurvivalClass result;

		result = this.survivalClassService.create();

		Assert.notNull(result);
	}

	@Test
	public void findAllTest() {

		Collection<SurvivalClass> result;

		result = this.survivalClassService.findAll();

		Assert.notNull(result);
		Assert.notEmpty(result);

	}

	@Test
	public void findOneTest() {

		Collection<SurvivalClass> survivalClasses;
		SurvivalClass result;
		int idSurvivalClass;

		survivalClasses = this.survivalClassService.findAll();
		idSurvivalClass = survivalClasses.iterator().next().getId();
		result = this.survivalClassService.findOne(idSurvivalClass);

		Assert.notNull(result);

	}

	@Test
	public void saveTest() {

		this.authenticate("manager1");

		SurvivalClass result;
		Manager manager;
		Trip trip;
		GPS location;
		SurvivalClass resultSaved;

		result = this.survivalClassService.create();

		manager = this.managerService.findAll().iterator().next();
		trip = manager.getTrips().iterator().next();

		location = new GPS();
		location.setLatitude(22.4);
		location.setLongitude(45.7);

		result.setTitle("title 1");
		result.setDescription("description 1");
		result.setLocation(location);
		result.setManager(manager);
		result.setTrip(trip);

		resultSaved = this.survivalClassService.save(result);

		Assert.notNull(resultSaved);

	}

	//	@Test
	//	public void deleteTest() {
	//
	//		this.authenticate("manager1");
	//
	//		SurvivalClass result;
	//		GPS location;
	//		Manager manager;
	//		Trip trip;
	//		SurvivalClass resultSave;
	//
	//		result = this.survivalClassService.create();
	//
	//		location = new GPS();
	//		location.setLatitude(22.4);
	//		location.setLongitude(45.7);
	//
	//		manager = this.managerService.findAll().iterator().next();
	//		trip = manager.getTrips().iterator().next();
	//
	//		result.setTitle("title 1");
	//		result.setDescription("description 1");
	//		result.setLocation(location);
	//		result.setManager(manager);
	//		result.setTrip(trip);
	//
	//		resultSave = this.survivalClassService.save(result);
	//
	//		this.survivalClassService.delete(result);
	//
	//		Assert.isNull(this.survivalClassService.findOne(resultSave.getId()));
	//
	//	}
}
