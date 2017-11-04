
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private EducationRecordRepository	educationRecordRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public EducationRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public EducationRecord create() {
		//TODO: Hacer
		return null;
	}
	public Collection<EducationRecord> findAll() {
		Collection<EducationRecord> educationRecords;

		educationRecords = this.educationRecordRepository.findAll();
		Assert.notNull(educationRecords);

		return educationRecords;
	}

	public EducationRecord findOne(int educationRecordId) {
		EducationRecord educationRecord;

		educationRecord = this.educationRecordRepository.findOne(educationRecordId);
		Assert.notNull(educationRecord);

		return educationRecord;
	}

	public EducationRecord save(EducationRecord educationRecord) {
		Assert.notNull(educationRecord);
		EducationRecord result;

		result = this.educationRecordRepository.saveAndFlush(educationRecord);

		return result;

	}

	public void delete(EducationRecord educationRecord) {
		//TODO: Hacer pero antes hay que mirar si se puede borrar del tiron.
	}

}
