
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public EndorserRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public EndorserRecord create() {
		EndorserRecord endorserRecord;
		endorserRecord = new EndorserRecord();

		List<String> comments = new ArrayList<String>();
		endorserRecord.setComments(comments);

		return endorserRecord;
	}
	public Collection<EndorserRecord> findAll() {
		Collection<EndorserRecord> endorserRecords;

		endorserRecords = this.endorserRecordRepository.findAll();
		Assert.notNull(endorserRecords);

		return endorserRecords;
	}

	public EndorserRecord findOne(int endorserRecordId) {
		Assert.isTrue(endorserRecordId != 0);
		EndorserRecord endorserRecord;

		endorserRecord = this.endorserRecordRepository.findOne(endorserRecordId);
		Assert.notNull(endorserRecord);

		return endorserRecord;
	}

	public EndorserRecord save(EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		Assert.isTrue(endorserRecord.getId() == 0);

		EndorserRecord newResult;

		newResult = this.endorserRecordRepository.saveAndFlush(endorserRecord);

		return newResult;

	}

	public void delete(EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		Assert.notNull(this.endorserRecordRepository.findOne(endorserRecord.getId()));

		this.endorserRecordRepository.delete(endorserRecord);
	}

	public Collection<EndorserRecord> saveAll(Collection<EndorserRecord> endorserRecords) {
		Assert.notNull(endorserRecords);
		List<EndorserRecord> newEndorserRecords = new ArrayList<EndorserRecord>();
		newEndorserRecords.addAll(this.endorserRecordRepository.save(endorserRecords));

		return newEndorserRecords;
	}

}
