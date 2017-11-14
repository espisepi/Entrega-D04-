
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CategoryServicetest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private CategoryService	categoryService;


	// Supporting services ----------------------------------------------------

	//	@Test
	//	public void testCreate() {
	//		super.authenticate("administrator1");
	//		Category category;
	//
	//		category = this.categoryService.create();
	//		Assert.notNull(category.getSubCategories());
	//
	//		super.unauthenticate();
	//	}
	//	@Test
	//	public void testSave() {
	//		super.authenticate("administrator1");
	//		Category result;
	//
	//		result = this.categoryService.create();
	//		result.setName("Name test");
	//		result.setFatherCategory(this.categoryService.findOne(super.getEntityId("climbing")));
	//		result = this.categoryService.save(result);
	//		Assert.isTrue(result.getId() != 0);
	//
	//		super.unauthenticate();
	//
	//	}

	@Test
	@Rollback(false)
	public void testDelete() {
		super.authenticate("administrator1");
		Category category;

		category = this.categoryService.findOne(super.getEntityId("category"));
		//Me coge la category.SubCategories() como null?
		System.out.println(category.getSubCategories().size());
		this.categoryService.delete(category);

		super.unauthenticate();
	}

	//	@Test
	//	public void testFindAllPositive() {
	//		Collection<Category> categorys;
	//		categorys = this.categoryService.findAll();
	//		Assert.notEmpty(categorys);
	//	}
	//
	//	@Test
	//	public void testFindOne() {
	//		Category category;
	//		category = this.categoryService.findOne(super.getEntityId("climbing"));
	//		Assert.notNull(category);
	//	}
}
