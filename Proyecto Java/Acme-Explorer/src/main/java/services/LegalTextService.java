
package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.LegalTextRepository;

@Service
@Transactional
public class LegalTextService {

	// Managed repository -----------------------------------------------------
	private LegalTextRepository	legalTextRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public LegalTextService() {
		super();
	}

}
