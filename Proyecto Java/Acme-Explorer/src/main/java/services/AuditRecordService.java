
package services;

import java.util.Collection;
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

	@Autowired
	private AuditorService			auditorService;


	// Constructors-------------------------------------------------------

	public AuditRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public AuditRecord create() {
		AuditRecord result;
		result = new AuditRecord();
		result.setDraftMode(true);
		return result;
	}

	public Collection<AuditRecord> findAll() {
		Collection<AuditRecord> result;
		result = this.auditRecordRepository.findAll();
		return result;
	}

	public AuditRecord findOne(final int auditrecordId) {
		AuditRecord result;
		result = this.auditRecordRepository.findOne(auditrecordId);
		if (result.isDraftMode())
			Assert.isTrue(result.getAuditor().getId() == this.auditorService.findByPrincipal().getId());
		Assert.notNull(result);
		return result;
	}

	public AuditRecord save(AuditRecord auditrecord) {
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
	// Other business methods------------------------------------------------------

	public Collection<AuditRecord> findAuditRecordInDraftMode(int auditorId) {
		Collection<AuditRecord> result;
		result = this.auditRecordRepository.findAuditRecordInDraftMode(auditorId);
		return result;
	}
}
