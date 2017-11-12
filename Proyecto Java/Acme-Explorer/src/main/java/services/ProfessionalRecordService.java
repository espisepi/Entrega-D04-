
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		ProfessionalRecord professionalRecord;
		professionalRecord = new ProfessionalRecord();
		List<String> comments = new ArrayList<String>();

		professionalRecord.setComments(comments);
		return professionalRecord;
	}

	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> professionalRecords;

		professionalRecords = this.professionalRecordRepository.findAll();
		Assert.notNull(professionalRecords);

		return professionalRecords;
	}

	public ProfessionalRecord findOne(int professionalRecordId) {
		Assert.isTrue(professionalRecordId != 0);
		ProfessionalRecord professionalRecord;

		professionalRecord = this.professionalRecordRepository.findOne(professionalRecordId);

		return professionalRecord;
	}

	public ProfessionalRecord save(ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);
		Assert.isTrue(professionalRecord.getId() == 0);
		ProfessionalRecord result;

		result = this.professionalRecordRepository.saveAndFlush(professionalRecord);
		Assert.isTrue(result.getId() != 0);
		return result;

	}

	public ProfessionalRecord update(ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);
		Assert.notNull(this.professionalRecordRepository.findOne(professionalRecord.getId()));

		ProfessionalRecord newProfessionalRecord = this.professionalRecordRepository.saveAndFlush(professionalRecord);

		return newProfessionalRecord;
	}
	public void delete(ProfessionalRecord professionalRecord) {

		Assert.notNull(professionalRecord);
		Assert.notNull(this.professionalRecordRepository.findOne(professionalRecord.getId()));

		this.professionalRecordRepository.delete(professionalRecord);
		Assert.isNull(this.professionalRecordRepository.findOne(professionalRecord.getId()));
	}

	public Collection<ProfessionalRecord> saveAll(Collection<ProfessionalRecord> professionalRecords) {
		Assert.notNull(professionalRecords);
		List<ProfessionalRecord> newProfessionalRecords = new ArrayList<ProfessionalRecord>();
		newProfessionalRecords.addAll(this.professionalRecordRepository.save(professionalRecords));

		return newProfessionalRecords;
	}
}
