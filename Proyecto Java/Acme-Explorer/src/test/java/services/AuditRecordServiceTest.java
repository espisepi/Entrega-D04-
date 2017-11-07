
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
import domain.AuditRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditRecordServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private AuditRecordService	auditRecordService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		AuditRecord result;
		result = this.auditRecordService.create();
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<AuditRecord> result = this.auditRecordService.findAll();
		Assert.notEmpty(result);
	}

	@Test
	public void testDeletePositive() {
		AuditRecord auditRecord;
		auditRecord = this.auditRecordService.findOne(6163);

		this.auditRecordService.delete(auditRecord);
	}

	//	@Test
	//	public void testDeleteNegative() {
	//		AuditRecord auditRecord;
	//		auditRecord = this.auditRecordService.findOne(6163);
	//
	//		this.auditRecordService.delete(auditRecord);
	//	}

}
