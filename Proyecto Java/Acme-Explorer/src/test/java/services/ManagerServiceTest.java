
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ManagerServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	private ManagerService	managerService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		this.authenticate("manager 1");

		Manager manager;
		manager = this.managerService.create();
		manager.setName("name1");
		manager.setSurname("surname1");
		manager.setEmail("manager1@gmail.com");

		Assert.notNull(manager);
	}

}
