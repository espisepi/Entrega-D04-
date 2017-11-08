
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Explorer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ExplorerServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private ExplorerService	explorerService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreatePositive() {
		Explorer explorer;
		explorer = this.explorerService.create();
		Assert.notNull(explorer);
		Assert.notEmpty(explorer.getMessagesFolders());
	}

	@Test
	public void testSavePositive() {
		Explorer explorer;
		explorer = this.explorerService.create();

		explorer.setName("name explorer test");
		explorer.setSurname("surname explorer test");
		explorer.setEmail("emailExplorerTest@email.com");
		explorer.setPhone("313(987)7121");
		explorer.setAddress("address explorer test");

		explorer = this.explorerService.save(explorer);
		Assert.notNull(explorer.getId());

	}

}
