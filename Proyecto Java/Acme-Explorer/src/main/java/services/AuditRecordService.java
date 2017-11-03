
package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditRecordRepository;
import domain.AuditRecord;

@Service
@Transactional
public class AuditRecordService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AuditRecordRepository	auditRecordRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public AuditRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public AuditRecord create() {
		AuditRecord result;
		result = new AuditRecord();
		Date realisedMoment;
		realisedMoment = new Date();
		result.setRealisedMoment(realisedMoment);
		return result;
	}

	public AuditRecord findOne(final int auditrecorId) {
		AuditRecord result;
		result = this.auditRecordRepository.findOne(auditrecorId);
		Assert.notNull(result);
		return result;
	}

	public AuditRecord save(AuditRecord auditrecord) {
		Assert.notNull(auditrecord);
		AuditRecord result;
		result = this.auditRecordRepository.save(auditrecord);
		Date realisedMoment;
		realisedMoment = new Date(System.currentTimeMillis() - 1000);
		result.setRealisedMoment(realisedMoment);
		Assert.notNull(result);
		return result;
	}

	public void delete(final AuditRecord auditrecord) {
		assert auditrecord != null;
		assert auditrecord.getId() != 0;
		Assert.isTrue(this.auditRecordRepository.exists(auditrecord.getId()));
		this.auditRecordRepository.delete(auditrecord);
	}

}
