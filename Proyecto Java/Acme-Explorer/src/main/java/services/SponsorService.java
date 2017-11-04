
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	// Managed repository -----------------------------------------------------
	private SponsorRepository	sponsorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public SponsorService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------
	public Sponsor create() {
		Sponsor result = new Sponsor();
		return result;
	}
	public Sponsor findOne(int idSponsor) {
		Assert.isTrue(idSponsor != 0);
		Sponsor result;
		result = this.sponsorRepository.findOne(idSponsor);
		return result;

	}
	public Collection<Sponsor> findAll() {
		Collection<Sponsor> result;
		result = this.sponsorRepository.findAll();
		return result;

	}
	public Sponsor save(Sponsor sponsor) {
		Assert.isTrue(sponsor != null);
		Sponsor result;
		result = this.sponsorRepository.save(sponsor);
		return result;

	}

	public void delete(Sponsor sponsor) {
		Assert.isTrue(this.sponsorRepository.findAll().contains(sponsor), "no existe ");
		this.sponsorRepository.delete(sponsor);

	}
	// Other business methods -------------------------------------------------

}
