
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
import domain.MiscellaneousRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MiscellaneousRecordTest extends AbstractTest {

	//Service under test-------------------------------

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	//Test

	@Test
	public void testCreatePositive() {
		MiscellaneousRecord miscellaneousRecord;
		miscellaneousRecord = this.miscellaneousRecordService.create();
		Assert.notNull(miscellaneousRecord);
	}

	@Test
	public void testFindAllPositive() {
		Collection<MiscellaneousRecord> miscellaneousRecords;
		miscellaneousRecords = this.miscellaneousRecordService.findAll();
		Assert.notEmpty(miscellaneousRecords);
	}

	@Test
	public void testFindOnePositive() {
		MiscellaneousRecord miscellaneousRecord;
		miscellaneousRecord = this.miscellaneousRecordService.findOne(6200);
		Assert.notNull(miscellaneousRecord);

	}

	@Test
	public void testDeletePositive() {
		MiscellaneousRecord miscellaneousRecord;
		miscellaneousRecord = this.miscellaneousRecordService.findOne(6200);

		this.miscellaneousRecordService.delete(miscellaneousRecord);
		Assert.isNull(this.miscellaneousRecordService.findOne(6200));
	}
}
