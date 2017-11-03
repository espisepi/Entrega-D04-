
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StoryRepository;
import domain.Story;

@Service
@Transactional
public class StoryService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private StoryRepository	storyRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public StoryService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Story create() {
		Story result;

		result = new Story();

		return result;
	}

	public Collection<Story> findAll() {
		Collection<Story> result;

		Assert.notNull(this.storyRepository);
		result = this.storyRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Story findOne(final int storyId) {
		Story result;

		result = this.storyRepository.findOne(storyId);

		return result;
	}

	public Story save(final Story story) {
		assert story != null;

		Story result;

		result = this.storyRepository.save(story);

		return result;
	}

	public void delete(final Story story) {
		assert story != null;
		assert story.getId() != 0;

		Assert.isTrue(this.storyRepository.exists(story.getId()));

		this.storyRepository.delete(story);
	}

	// Other business methods -------------------------------------------------
}