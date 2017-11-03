
package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.TripRepository;

@Service
@Transactional
public class TripService {

	// Managed repository -----------------------------------------------------
	private TripRepository	tripRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public TripService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
}
