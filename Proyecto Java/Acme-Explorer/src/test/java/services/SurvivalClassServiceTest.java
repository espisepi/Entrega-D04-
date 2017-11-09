
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.GPS;
import domain.SurvivalClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SurvivalClassServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private SurvivalClassService	survivalClassService;


	// Tests ----------------------------------------------

	@Test
	public void createTest() {

		SurvivalClass result;
		GPS coordinates;

		coordinates = new GPS();
		coordinates.setLatitude(26.9);
		coordinates.setLongitude(63.9);

		result = this.survivalClassService.create();
		result.setTitle("Title 1");
		result.setDescription("Description 1");
		result.setLocation(coordinates);

		Assert.notNull(result);
	}

}
