
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
import domain.Auditor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditorServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private AuditorService	auditorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		this.authenticate("auditor 1");

		Auditor auditor;

		auditor = this.auditorService.create();
		auditor.setName("name1");
		auditor.setSurname("surname1");
		auditor.setEmail("admin1@gmail.com");
		auditor.setPhone("7777");
		auditor.setAddress("addres1");

		this.auditorService.save(auditor);
	}

	@Test
	public void testFindAll() {
		Collection<Auditor> auditors;
		auditors = this.auditorService.findAll();
		Assert.notEmpty(auditors);
		Assert.notNull(auditors);
	}

}
