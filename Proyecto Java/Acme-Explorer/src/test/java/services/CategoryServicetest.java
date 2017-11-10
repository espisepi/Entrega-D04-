
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	private CategoryService	catergoryService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreate() {
		super.authenticate("administrator1");
		Category category;

		category = this.catergoryService.create();
		Assert.notNull(category.getSubCategories());

		super.unauthenticate();
	}
	@Test
	public void testSave() {
		super.authenticate("administrator1");
		Category result;

		result = this.catergoryService.create();
		result.setName("Name test");
		result = this.catergoryService.save(result);
		Assert.isTrue(result.getId() != 0);

		super.unauthenticate();

	}
}
