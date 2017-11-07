
package services;

import java.util.Collection;
import java.util.Iterator;

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
		this.authenticate("administrator1");
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
		this.authenticate("administrator1");
		Collection<Administrator> administrators;
		Iterator<Administrator> administrator; // para poder recorrer la colección

		administrators = this.administratorService.findAll();
		System.out.println(this.administratorService.findAll());
		administrator = administrators.iterator();

		this.administratorService.delete(administrator.next());
		System.out.println(this.administratorService.findAll());

	}

	@Test
	public void testFindAll() {
		Collection<Administrator> administrators;
		administrators = this.administratorService.findAll();
		Assert.notEmpty(administrators);
		Assert.notNull(administrators);
	}

	//	@Test
	//	public void testFindOne() {
	//		Administrator administrator;
	//		administrator = this.administratorService.findOne(6122);
	//		Assert.notNull(administrator);
	//	}
}
