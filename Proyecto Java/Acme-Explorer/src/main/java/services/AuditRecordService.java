
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditRecordRepository;

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
}
