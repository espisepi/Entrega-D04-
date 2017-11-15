
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Trip;

@Service
@Transactional
public class CategoryService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CategoryRepository			categoryRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private TripService					tripService;
	@Autowired
	private ConfigurationSystemService	configurationSystemService;
	@Autowired
	private AdministratorService		administratorService;


	// Constructors------------------------------------------------------------
	public CategoryService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Category create() {
		Category result;
		Collection<Category> subCategories;

		Assert.notNull(this.administratorService.findByPrincipal());
		result = new Category();
		subCategories = new ArrayList<Category>();
		result.setSubCategories(subCategories);

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
		Assert.notNull(result);
		return result;
	}

	public Category save(final Category category) {
		Assert.notNull(category);
		Assert.notNull(this.administratorService.findByPrincipal());

		Category result;

		result = this.categoryRepository.save(category);
		//No se añade ninguna Trip al crearse una category porque asi lo dice en los requisitos

		return result;
	}

	public void delete(final Category category) {
		Collection<Trip> tripsWithThisCategory;
		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);
		Assert.isTrue(this.categoryRepository.exists(category.getId()));
		this.administratorService.checkPrincipal();
		tripsWithThisCategory = this.tripService.findAllTripsByCategoryId(category.getId());
		Assert.isTrue(!(this.configurationSystemService.defaultCategories().contains(category)));

		if (category.getSubCategories().isEmpty())
			this.categoryRepository.delete(category);
		else {
			for (final Category subCategory : category.getSubCategories())
				this.delete(subCategory);
			this.categoryRepository.delete(category);
		}
		if (tripsWithThisCategory != null)
			for (final Trip trip : tripsWithThisCategory)
				trip.getCategories().remove(category);
	}
}
