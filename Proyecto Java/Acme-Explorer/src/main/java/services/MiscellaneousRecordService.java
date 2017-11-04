
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MiscellaneousRecordRepository	miscellaneousRecordRepository;


	// Constructors-------------------------------------------------------

	public MiscellaneousRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public MiscellaneousRecord create() {
		// TODO: Auto-generated method stub
		return null;
	}

	public Collection<MiscellaneousRecord> findAll() {
		// TODO: hacer
		return null;
	}

	public MiscellaneousRecord findOne(int miscellaneousRecordId) {

		// TODO: HACER
		return null;
	}

	public MiscellaneousRecord save(MiscellaneousRecord miscellaneousRecord) {

		// TODO : hacer
		return null;
	}

	public void delete(MiscellaneousRecord miscellaneousRecord) {

		// TODO: HACER

	}
}
