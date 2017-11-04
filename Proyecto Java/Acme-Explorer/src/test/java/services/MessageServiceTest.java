
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Message;
import domain.MessageFolder;

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


	//	@Test
	//	public void testCreatePositive() {
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
	public void testSavePositive() {
		this.authenticate("Explorer 4");
		Message message1;
		message1 = this.messageService.create();
		Actor recipient;
		MessageFolder folder;

		recipient = this.actorService.findAll().iterator().next();
		folder = this.messageFolderService.create();
		folder.setName("BASURA");
		folder.setModifiable(true);
		message1.setMessageFolder(folder);
		folder.getMessages().add(message1);
		message1.setBody("hola caracola");
		message1.setRecipient(recipient);
		message1.setPriority("NEUTRAL");
		message1.setSubject("hola");
		this.messageService.Save(message1);
		Assert.isTrue(this.messageService.findAll().contains(message1));

	}
}
