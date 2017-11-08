
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

	// Constructors-------------------------------------------------------

	public AuditRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public AuditRecord create() {
		AuditRecord result;
		result = new AuditRecord();
		result.setDraftMode(true); // Una vez que se crea está en modo borrador
		Date realisedMoment;
		realisedMoment = new Date();
		result.setRealisedMoment(realisedMoment);
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
		result = this.auditRecordRepository.save(auditrecord);
		return result;
	}

	public void delete(final AuditRecord auditrecord) {
		//se puede borrar o modificar si está en modo borrador
		Assert.isTrue(auditrecord.isDraftMode() == true);
		this.auditRecordRepository.delete(auditrecord);
	}

	// Other business methods------------------------------------------------------

	public Collection<AuditRecord> findAllAuditRecordInDraftMode() {
		Collection<AuditRecord> result;
		result = this.auditRecordRepository.findAllAuditRecordInDraftMode();
		return result;
	}
}
