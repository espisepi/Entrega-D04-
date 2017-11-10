
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
		//final Actor actorcreator = this.actorService.findPrincipal();
		final MessageFolder messagefolder = new MessageFolder();
		final List<Message> message = new ArrayList<>();
		messagefolder.setMessages(message);
		//actorcreator.getMessagesFolders().add(messagefolder);, lo pongo en el save

		return messagefolder;
	}

	public MessageFolder findOne(final int mFid) {
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
	public MessageFolder save(final MessageFolder messageFolder) {
		final Actor actorPrincipal = this.actorService.findPrincipal();
		Assert.notNull(actorPrincipal);
		Assert.notNull(messageFolder);
		MessageFolder res;
		res = this.messageFolderRepository.save(messageFolder);
		//Se le añade al actor el messageFolder INSTRUMENTADO
		actorPrincipal.getMessagesFolders().add(res);
		//actorCreator = this.actorService.save(actorCreator); No hace falta hacerlo porque con el add te lo añade a la BD
		//Assert.notNull(actorCreator);
		Assert.notNull(res);
		return res;
	}
	public void delete(final MessageFolder messageFolder) {
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
		final Collection<MessageFolder> messagesFolders = this.messageFolderRepository.ActorFolders(actor.getId());
		return messagesFolders;

	}

	public Collection<MessageFolder> createDefaultFolders() {
		Collection<MessageFolder> res;
		res = new ArrayList<MessageFolder>();
		final Collection<Message> messages = new ArrayList<>();

		MessageFolder inbox = new MessageFolder();
		MessageFolder notificationbox = new MessageFolder();
		MessageFolder outbox = new MessageFolder();
		MessageFolder trashbox = new MessageFolder();
		MessageFolder spambox = new MessageFolder();
		inbox.setModifiable(false);
		outbox.setModifiable(false);
		notificationbox.setModifiable(false);
		trashbox.setModifiable(false);
		spambox.setModifiable(false);

		inbox.setMessages(messages);
		outbox.setMessages(messages);
		notificationbox.setMessages(messages);
		trashbox.setMessages(messages);
		spambox.setMessages(messages);

		inbox.setName("in box");
		outbox.setName("out box");
		notificationbox.setName("Notification box");
		trashbox.setName("trash box");
		spambox.setName("spam box");

		inbox = this.messageFolderRepository.save(inbox);
		outbox = this.messageFolderRepository.save(outbox);
		notificationbox = this.messageFolderRepository.save(notificationbox);
		trashbox = this.messageFolderRepository.save(trashbox);
		spambox = this.messageFolderRepository.save(spambox);

		res.add(inbox);
		res.add(outbox);
		res.add(notificationbox);
		res.add(trashbox);
		res.add(spambox);

		return res;

	}

	public MessageFolder returnOutboxFolder(Actor actor) {
		MessageFolder res = null;
		Collection<MessageFolder> messagefolders;
		messagefolders = actor.getMessagesFolders();
		for (final MessageFolder folder : messagefolders)
			if (folder.getName().equals("out box")) {
				res = folder;
				break;
			}

		return res;
	}

}
