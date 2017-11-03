
package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.StageRepository;

@Service
@Transactional
public class StageService {

	// Managed repository -----------------------------------------------------
	private StageRepository	stageRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public StageService() {
		super();
	}

}
