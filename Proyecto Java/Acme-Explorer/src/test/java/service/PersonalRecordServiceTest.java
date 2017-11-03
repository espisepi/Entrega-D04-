
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.PersonalRecordService;
import utilities.AbstractTest;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PersonalRecordServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private PersonalRecordService	personalRecordService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreatePositive() {
		PersonalRecord personalRecord = this.personalRecordService.create();
		Assert.isTrue(personalRecord != null);

	}
}
