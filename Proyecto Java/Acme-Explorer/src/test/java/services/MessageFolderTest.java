
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;
import domain.MessageFolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MessageFolderTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private MessageFolderService	messageFolderService;
	@Autowired
	private AdministratorService	administratorService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreate() {
		this.authenticate("Explorer 5");
		MessageFolder messageFolder;
		messageFolder = this.messageFolderService.create();
		Assert.notNull(messageFolder);
		this.unauthenticate();
	}

	@Test
	public void testSave() {
		this.authenticate("Explorer 5");
		MessageFolder messageFolder = new MessageFolder();
		messageFolder.setModifiable(true);
		messageFolder.setName("Coordinador");
		this.messageFolderService.save(messageFolder);

	}

	@Test
	public void testdelete() {
		MessageFolder messageFolder = this.messageFolderService.findAll().iterator().next();
		messageFolder.setModifiable(true);
		this.messageFolderService.delete(messageFolder);

	}

	@Test
	public void testCreateDefaultChapter() {
		Administrator administrator;
		administrator = this.administratorService.create();

		administrator.setName("name");
		administrator.setSurname("surname");
		administrator.setEmail("email@gmail.com");
		administrator.setPhone("31333");
		administrator.setAddress("address");

		administrator = this.administratorService.save(administrator);

	}
}
