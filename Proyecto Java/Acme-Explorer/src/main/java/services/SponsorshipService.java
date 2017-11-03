
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SponsorshipRepository;

@Service
@Transactional
public class SponsorshipService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SponsorshipRepository	sponsorshipRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public SponsorshipService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

}
