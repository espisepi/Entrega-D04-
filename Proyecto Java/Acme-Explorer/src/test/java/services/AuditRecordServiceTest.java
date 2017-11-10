
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Attachment;
import domain.AuditRecord;
import domain.Auditor;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditRecordServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private AuditRecordService	auditRecordService;

	@Autowired
	private AuditorService		auditorService;

	@Autowired
	private TripService			tripService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		this.authenticate("auditor4");
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
	public void testSave() {
		this.authenticate("auditor4");
		AuditRecord auditRecord;
		Auditor auditor;
		Trip trip1;
		Collection<Attachment> attachments;

		auditRecord = this.auditRecordService.create();

		trip1 = this.tripService.findOne(super.getEntityId("trip1"));
		attachments = new ArrayList<Attachment>();

		auditRecord.setTrip(trip1);
		auditRecord.setTitle("title1");
		auditRecord.setDescription("description1");
		auditRecord.setDraftMode(true);
		auditRecord.setAttachments(attachments);

		auditRecord = this.auditRecordService.save(auditRecord);
		auditor = this.auditorService.findByPrincipal();
		Assert.notNull(auditor);
		//Assert.isTrue(trip1.getAuditRecords().contains(auditRecord));
		//Assert.isTrue(auditor.getAuditRecords().contains(auditRecord));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleleNegative() {
		Auditor auditor;
		AuditRecord auditRecord;
		Trip trip;

		auditRecord = this.auditRecordService.create();
		auditor = this.auditorService.findAll().iterator().next();
		trip = this.tripService.findAll().iterator().next();

		auditRecord.setAuditor(auditor);
		auditRecord.setTrip(trip);
		auditRecord.setTitle("title2");
		auditRecord.setDescription("description2");
		auditRecord.setDraftMode(false);

		auditRecord = this.auditRecordService.save(auditRecord);
		this.auditRecordService.delete(auditRecord);

	}

	@Test
	public void testDelelePositive() {
		AuditRecord auditRecord;
		auditRecord = this.auditRecordService.findOne(super.getEntityId("auditrecord1"));
		this.auditRecordService.delete(auditRecord);

	}

	@Test
	public void testfindOne() {
		Collection<AuditRecord> auditrecords;
		AuditRecord auditrecord;

		auditrecords = this.auditRecordService.findAll();
		Assert.notNull(auditrecords);
		Assert.notEmpty(auditrecords);

		auditrecord = this.auditRecordService.findOne(auditrecords.iterator().next().getId());
		Assert.notNull(auditrecord);
	}

}
