
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StageRepository;
import domain.Stage;

@Service
@Transactional
public class StageService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private StageRepository	stageRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public StageService() {
		super();
	}

	public Stage create() {
		Stage result;
		result = new Stage();
		return result;
	}

	public Collection<Stage> findAll() {
		Collection<Stage> result;
		Assert.notNull(this.stageRepository);
		result = this.stageRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Stage findOne(final int stageId) {
		Stage result;
		result = this.stageRepository.findOne(stageId);
		return result;
	}

	public Stage save(final Stage stage) {
		assert stage != null;

		Stage result;

		result = this.stageRepository.save(stage);

		return result;
	}

	public void delete(final Stage stage) {
		assert stage != null;
		assert stage.getId() != 0;

		Assert.isTrue(this.stageRepository.exists(stage.getId()));

		this.stageRepository.delete(stage);
	}

}
