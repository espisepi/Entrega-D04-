
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TagServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	private TagService	tagService;


	// Supporting services ----------------------------------------------------

	// Test

	@Test
	public void testCreate() {
		Tag tag = this.tagService.create();
		Assert.notNull(tag);

	}

	@Test
	public void testSave() {
		Tag tag = this.tagService.create();

		tag.setName("tag 11");

		Tag newTag = this.tagService.save(tag);
		Assert.notNull(newTag);
	}

	@Test
	public void testDelete() {
		Tag tag = this.tagService.create();

		tag.setName("tag 11");

		Tag newTag = this.tagService.save(tag);
		Assert.notNull(newTag);

		this.tagService.delete(newTag);

	}
}
