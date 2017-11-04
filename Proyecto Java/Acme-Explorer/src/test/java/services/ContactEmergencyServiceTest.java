
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ContactEmergency;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ContactEmergencyServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private ContactEmergencyService	contactEmergencyService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreatePositive() {
		ContactEmergency contactEmergency;
		contactEmergency = this.contactEmergencyService.create();
		Assert.notNull(contactEmergency);
	}

	@Test
	public void testSavePositive() {
		ContactEmergency contactEmergency;
		contactEmergency = this.contactEmergencyService.create();

		contactEmergency.setName("name contactEmergency test");
		contactEmergency.setEmail("email contactEmergency test");

		contactEmergency = this.contactEmergencyService.save(contactEmergency);
		Assert.notNull(contactEmergency.getId());

	}
	@Test
	public void testFindAllPositive() {
		Collection<ContactEmergency> contactEmergencys;
		contactEmergencys = this.contactEmergencyService.findAll();
		Assert.notEmpty(contactEmergencys);
	}

	//	@Test
	//	public void testFindOnePositive() {
	//		ContactEmergency contactEmergency;
	//		contactEmergency = this.contactEmergencyService.findOne(6248);
	//		Assert.notNull(contactEmergency);
	//	}
}
