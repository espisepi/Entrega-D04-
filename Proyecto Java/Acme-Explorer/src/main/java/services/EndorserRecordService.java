
package services;

import java.util.Collection;

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
		//TODO: Hacer
		return null;
	}
	public Collection<EndorserRecord> findAll() {
		Collection<EndorserRecord> endorserRecords;

		endorserRecords = this.endorserRecordRepository.findAll();
		Assert.notNull(endorserRecords);

		return endorserRecords;
	}

	public EndorserRecord findOne(int endorserRecordId) {
		EndorserRecord endorserRecord;

		endorserRecord = this.endorserRecordRepository.findOne(endorserRecordId);
		Assert.notNull(endorserRecord);

		return endorserRecord;
	}

	public EndorserRecord save(EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		EndorserRecord result;

		result = this.endorserRecordRepository.saveAndFlush(endorserRecord);

		return result;

	}

	public void delete(EndorserRecord endorserRecord) {
		//TODO: Hacer pero antes hay que mirar si se puede borrar del tiron.
	}

}
