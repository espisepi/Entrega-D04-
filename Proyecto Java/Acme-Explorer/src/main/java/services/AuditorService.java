
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditorRepository;

@Service
@Transactional
public class AuditorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AuditorRepository	auditorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public AuditorService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------
}
