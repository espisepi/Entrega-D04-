
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	@Test
	public void testDeletePositive() {

		PersonalRecord personalRecord = this.personalRecordService.findOne(6179);
		this.personalRecordService.delete(personalRecord);

		Assert.isNull(this.personalRecordService.findOne(6179));
	}
	@Test
	public void testSavePositive() {
		PersonalRecord personalRecord;
		personalRecord = this.personalRecordService.create();

		personalRecord.setFullName("personalRecord 8");
		personalRecord.setEmail("dany@gmail.com");
		personalRecord.setLinkedProfile("https://www.example.com");
		personalRecord.setPhoto("https://www.example.com");
		personalRecord.setPhone("+34(578)1239");

		PersonalRecord newPersonalRecord = this.personalRecordService.save(personalRecord);
		Assert.notNull(newPersonalRecord);
	}
}
