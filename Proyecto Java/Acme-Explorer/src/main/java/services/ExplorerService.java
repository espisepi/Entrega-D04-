
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExplorerRepository;
import domain.Explorer;

@Service
@Transactional
public class ExplorerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ExplorerRepository	explorerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ExplorerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Explorer create() {
		Explorer result;

		result = new Explorer();

		return result;
	}

	public Collection<Explorer> findAll() {
		Collection<Explorer> result;

		Assert.notNull(this.explorerRepository);
		result = this.explorerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Explorer findOne(final int explorerId) {
		Explorer result;

		result = this.explorerRepository.findOne(explorerId);

		return result;
	}

	public Explorer save(final Explorer explorer) {
		assert explorer != null;

		Explorer result;

		result = this.explorerRepository.save(explorer);

		return result;
	}

	public void delete(final Explorer explorer) {
		assert explorer != null;
		assert explorer.getId() != 0;

		Assert.isTrue(this.explorerRepository.exists(explorer.getId()));

		this.explorerRepository.delete(explorer);
	}

	// Other business methods -------------------------------------------------
}
