
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CategoryRepository	categoryRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public CategoryService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Category create() {
		Category result;
		result = new Category();
		return result;
	}

	public Collection<Category> findAll() {
		Collection<Category> result;
		Assert.notNull(this.categoryRepository);
		result = this.categoryRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Category findOne(final int categoryId) {
		Category result;
		result = this.categoryRepository.findOne(categoryId);
		return result;
	}

	public Category save(final Category category) {
		assert category != null;

		Category result;

		result = this.categoryRepository.save(category);

		return result;
	}

	public void delete(final Category category) {
		assert category != null;
		assert category.getId() != 0;

		Assert.isTrue(this.categoryRepository.exists(category.getId()));

		this.categoryRepository.delete(category);
	}
}
