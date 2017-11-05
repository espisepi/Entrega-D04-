
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageFolderRepository;
import domain.Actor;
import domain.Message;
import domain.MessageFolder;

@Service
@Transactional
public class MessageFolderService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private MessageFolderRepository	messageFolderRepository;
	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;


	// Constructors -----------------------------------------------------------
	public MessageFolderService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------
	public MessageFolder create() {
		Actor actorcreator = this.actorService.findPrincipal();
		MessageFolder messagefolder = new MessageFolder();
		List<Message> message = new ArrayList<>();
		messagefolder.setMessages(message);
		actorcreator.getMessagesFolders().add(messagefolder);

		return messagefolder;
	}

	public MessageFolder findOne(int mFid) {
		Assert.isTrue(mFid != 0);

		MessageFolder result;

		result = this.messageFolderRepository.findOne(mFid);
		Assert.notNull(result);
		return result;

	}

	public Collection<MessageFolder> findAll() {

		Collection<MessageFolder> messagefolder;
		messagefolder = this.messageFolderRepository.findAll();
		return messagefolder;
	}
	public MessageFolder save(MessageFolder messageFolder) {
		Assert.isTrue(this.actorService.isAuthenticated());
		Assert.notNull(messageFolder);
		MessageFolder res;
		res = this.messageFolderRepository.save(messageFolder);
		return res;
	}
	public void delete(MessageFolder messageFolder) {
		Assert.notNull(messageFolder);
		Assert.isTrue(messageFolder.isModifiable() == true);
		Assert.isTrue(messageFolder.getId() != 0);
		Assert.isTrue(this.messageFolderRepository.exists(messageFolder.getId()));
		this.messageFolderRepository.delete(messageFolder);

	}
	// Other business methods -------------------------------------------------

	public Collection<MessageFolder> findAllByActorAutenticate() {
		Actor actor;
		actor = this.actorService.findPrincipal();
		Collection<MessageFolder> messagesFolders = this.messageFolderRepository.ActorFolders(actor.getId());
		return messagesFolders;

	}

	public Collection<MessageFolder> createDefaultFolders() {
		Collection<MessageFolder> res;
		MessageFolder inbox, outbox, notificationbox, trashbox, spambox;
		res = new ArrayList<MessageFolder>();

		inbox = this.create();
		outbox = this.create();
		notificationbox = this.create();
		trashbox = this.create();
		spambox = this.create();

		inbox.setModifiable(false);
		outbox.setModifiable(false);
		trashbox.setModifiable(false);
		spambox.setModifiable(false);

		inbox.setName("in box");
		outbox.setName("out box");
		notificationbox.setName("Notification box");
		trashbox.setName("trash box");
		spambox.setName("spam box");

		inbox = this.save(inbox);
		outbox = this.save(outbox);
		notificationbox = this.save(notificationbox);
		trashbox = this.save(trashbox);
		spambox = this.save(spambox);

		res.add(inbox);
		res.add(outbox);
		res.add(notificationbox);
		res.add(trashbox);
		res.add(spambox);

		return res;

	}
}
