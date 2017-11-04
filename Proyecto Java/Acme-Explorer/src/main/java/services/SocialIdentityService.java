
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SocialIdentityRepository;

@Service
@Transactional
public class SocialIdentityService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorService				actorService;


	// Constructors -----------------------------------------------------------
	public SocialIdentityService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

}
