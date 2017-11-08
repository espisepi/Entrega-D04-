
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

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

	@Test
	@Rollback(false)
	public void testSave() {
		this.authenticate("administrator1");
		Message message1;
		message1 = this.messageService.create();
		final Administrator principal;
		Administrator administrator;

		principal = this.administratorService.findByPrincipal();
		principal.getMessagesFolders().addAll(this.messageFolderService.createDefaultFolders());
		//		administrator = this.administratorService.create();
		//		administrator.setName("name");
		//		administrator.setSurname("surname");
		//		administrator.setEmail("email@gmail.com");
		//		administrator.setPhone("31333");
		//		administrator.setAddress("address");
		//		administrator = this.administratorService.save(administrator); 
		administrator = this.administratorService.findOne(super.getEntityId("administrator4"));
		administrator.getMessagesFolders().addAll(this.messageFolderService.createDefaultFolders());
		//administrator = this.administratorService.save(administrator);

		//principal = this.administratorService.save(principal);

		message1.setBody("hola caracola");
		message1.setRecipient(administrator);
		message1.setPriority("NEUTRAL");
		message1.setSubject("hola");

		this.messageService.Save(message1);
		Assert.isTrue(this.messageService.findAll().contains(message1));

	}
}
