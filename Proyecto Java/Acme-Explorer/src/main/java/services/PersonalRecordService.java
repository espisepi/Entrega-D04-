
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PersonalRecordRepository	personalRecordRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public PersonalRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public PersonalRecord create() {
		PersonalRecord personalRecord;
		personalRecord = new PersonalRecord();

		return personalRecord;
	}

	public Collection<PersonalRecord> findAll() {
		Collection<PersonalRecord> personalRecords;

		personalRecords = this.personalRecordRepository.findAll();
		Assert.notNull(personalRecords);

		return personalRecords;
	}

	public PersonalRecord findOne(int personalRecordId) {
		PersonalRecord personalRecord;

		personalRecord = this.personalRecordRepository.findOne(personalRecordId);
		Assert.notNull(personalRecord);

		return personalRecord;
	}

	public PersonalRecord save(PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);
		PersonalRecord result;

		result = this.personalRecordRepository.saveAndFlush(personalRecord);

		return result;

	}

	public Integer exitsCurriculaWithThisPersonalRecord(int personalRecordId) {

		Assert.isTrue(personalRecordId != 0);
		return this.personalRecordRepository.exitsCurriculaWithThisPersonalRecord(personalRecordId);
	}

	public void delete(PersonalRecord personalRecord) {

		assert personalRecord != null;
		assert personalRecord.getId() != 0;

		Assert.isTrue(this.personalRecordRepository.exitsCurriculaWithThisPersonalRecord(personalRecord.getId()) == 0, "Debe de eliminar antes la curricula asociada a este PersonalRecord");

		Assert.isTrue(this.personalRecordRepository.exists(personalRecord.getId()));

		this.personalRecordRepository.delete(personalRecord);

	}

}
