
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private AdministratorService	administratorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		Administrator administrator;
		administrator = this.administratorService.create();
		Assert.notNull(administrator);
	}

	@Test
	public void testSave() {
		Administrator administrator;
		administrator = this.administratorService.create();

		administrator.setName("name");
		administrator.setSurname("surname");
		administrator.setEmail("email@gmail.com");
		administrator.setPhone("31333");
		administrator.setAddress("address");

		administrator = this.administratorService.save(administrator);
		Assert.notNull(administrator.getId());

	}

	@Test
	public void testDelete() {
		Administrator administrator;
		administrator = this.administratorService.findOne(super.getEntityId("administrator1"));
		this.administratorService.delete(administrator);

	}

	@Test
	public void testFindAll() {
		Collection<Administrator> administrators;
		administrators = this.administratorService.findAll();
		Assert.notEmpty(administrators);
		Assert.notNull(administrators);
	}

	@Test
	public void testFindOne() {
		Administrator administrator;
		administrator = this.administratorService.findOne(super.getEntityId("administrator1"));
		Assert.notNull(administrator);
	}
}
