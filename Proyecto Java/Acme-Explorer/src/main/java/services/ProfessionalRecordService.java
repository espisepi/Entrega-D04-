
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public ProfessionalRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public ProfessionalRecord create() {
		//TODO: Hacer
		return null;
	}
	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> professionalRecords;

		professionalRecords = this.professionalRecordRepository.findAll();
		Assert.notNull(professionalRecords);

		return professionalRecords;
	}

	public ProfessionalRecord findOne(int professionalRecordId) {
		ProfessionalRecord professionalRecord;

		professionalRecord = this.professionalRecordRepository.findOne(professionalRecordId);
		Assert.notNull(professionalRecord);

		return professionalRecord;
	}

	public ProfessionalRecord save(ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);
		ProfessionalRecord result;

		result = this.professionalRecordRepository.saveAndFlush(professionalRecord);

		return result;

	}

	public void delete(ProfessionalRecord professionalRecord) {
		//TODO: Hacer pero antes hay que mirar si se puede borrar del tiron.
	}

}
