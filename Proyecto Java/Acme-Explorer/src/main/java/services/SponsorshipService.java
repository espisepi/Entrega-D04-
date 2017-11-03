
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

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

	public Sponsorship create() {
		Sponsorship result;
		result = new Sponsorship();
		return result;
	}

	public Sponsorship findOne(final int sponsorshipId) {
		Sponsorship result;
		result = this.sponsorshipRepository.findOne(sponsorshipId);
		Assert.notNull(result);
		return result;
	}

	public Sponsorship save(Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		Sponsorship result;
		result = this.sponsorshipRepository.save(sponsorship);
		Assert.notNull(result);
		return result;
	}

	public void delete(final Sponsorship sponsorship) {
		assert sponsorship != null;
		assert sponsorship.getId() != 0;
		Assert.isTrue(this.sponsorshipRepository.exists(sponsorship.getId()));
		this.sponsorshipRepository.delete(sponsorship);
	}

}
