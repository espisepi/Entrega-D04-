
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TagRepository;
import domain.Tag;

@Service
@Transactional
public class TagService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private TagRepository	tagRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public TagService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Tag create() {
		Tag result;
		result = new Tag();
		return result;
	}

	public Collection<Tag> findAll() {
		Collection<Tag> result;
		Assert.notNull(this.tagRepository);
		result = this.tagRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Tag findOne(final int tagId) {
		Tag result;
		result = this.tagRepository.findOne(tagId);
		return result;
	}

	public Tag save(final Tag tag) {
		assert tag != null;

		Tag result;

		result = this.tagRepository.save(tag);

		return result;
	}

	public void delete(final Tag tag) {
		assert tag != null;
		assert tag.getId() != 0;

		Assert.isTrue(this.tagRepository.exists(tag.getId()));

		this.tagRepository.delete(tag);
	}
}
