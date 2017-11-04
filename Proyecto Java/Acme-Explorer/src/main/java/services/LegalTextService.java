
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LegalTextRepository;
import domain.LegalText;

@Service
@Transactional
public class LegalTextService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private LegalTextRepository	legalTextRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public LegalTextService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public LegalText create() {
		LegalText result;
		result = new LegalText();
		return result;
	}

	public Collection<LegalText> findAll() {
		Collection<LegalText> result;
		Assert.notNull(this.legalTextRepository);
		result = this.legalTextRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public LegalText findOne(final int legalTextId) {
		LegalText result;
		result = this.legalTextRepository.findOne(legalTextId);
		return result;
	}

	public LegalText save(final LegalText legalText) {
		assert legalText != null;

		LegalText result;

		result = this.legalTextRepository.save(legalText);

		return result;
	}

	public void delete(final LegalText legalText) {
		assert legalText != null;
		assert legalText.getId() != 0;

		Assert.isTrue(this.legalTextRepository.exists(legalText.getId()));

		this.legalTextRepository.delete(legalText);
	}
}
