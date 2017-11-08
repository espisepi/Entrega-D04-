
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Administrator;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private MessageService			messageService;
	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorService			actorService;
	@Autowired
	private MessageFolderService	messageFolderService;
	@Autowired
	private AdministratorService	administratorService;


	//	@Test
	//	public void testCreate() {
	//		this.authenticate("Explorer 5");
	//		Message message;
	//		message = this.messageService.create();
	//		Assert.notNull(message);
	//		this.unauthenticate();
	//	}
	//
	//	@Test
	//	public void testFindOneAndFindAllPositive() {
	//		Collection<Message> messages;
	//		Message message;
	//		int id;
	//		messages = this.messageService.findAll();
	//		id = messages.iterator().next().getId();
	//		message = this.messageService.findOne(id);
	//		Assert.notNull(message);
	//
	//	}
	//	@Test
	//	public void testAdmin() {
	//		Administrator administratorSen;
	//		administratorSen = this.administratorService.create();
	//		administratorSen.setName("enviador");
	//		administratorSen.setSurname("surname");
	//		administratorSen.setEmail("email@gmail.com");
	//		administratorSen.setPhone("31333");
	//		administratorSen.setAddress("address");
	//		administratorSen.getUserAccount().setPassword("enviador");
	//		administratorSen.getUserAccount().setUsername("enviador");
	//		administratorSen.getMessagesFolders().addAll(this.messageFolderService.createDefaultFolders());
	//		this.administratorService.save(administratorSen);
	//
	//	}
	@Test
	@Rollback(false)
	public void testSave() {

		Administrator administratorSen, adminRecip;
		administratorSen = this.administratorService.create();
		administratorSen.setName("enviador");
		administratorSen.setSurname("surname");
		administratorSen.setEmail("email@gmail.com");
		administratorSen.setPhone("31333");
		administratorSen.setAddress("address");
		administratorSen.getUserAccount().setPassword("enviador");
		administratorSen.getUserAccount().setUsername("enviador");
		administratorSen.getMessagesFolders().addAll(this.messageFolderService.createDefaultFolders());
		this.administratorService.save(administratorSen);
		this.authenticate("enviador");
		Message message1;
		message1 = this.messageService.create();
		adminRecip = this.administratorService.create();
		adminRecip.setName("name");
		adminRecip.setSurname("surname");
		adminRecip.setEmail("email@gmail.com");
		adminRecip.setPhone("31333");
		adminRecip.setAddress("address");
		adminRecip.getMessagesFolders().addAll(this.messageFolderService.createDefaultFolders());

		administratorSen.getUserAccount().setPassword("recibidor");
		administratorSen.getUserAccount().setUsername("recibidor");
		this.administratorService.save(adminRecip);
		adminRecip = this.administratorService.findOne(super.getEntityId("administrator4"));

		//administrator = this.administratorService.save(administrator);

		//principal = this.administratorService.save(principal);

		message1.setBody("hola caracola");
		message1.setRecipient(adminRecip);
		message1.setPriority("NEUTRAL");
		message1.setSubject("hola");

		this.messageService.Save(message1);
		//Assert.isTrue(this.messageService.findAll().contains(message1));

	}
}
