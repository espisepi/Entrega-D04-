
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private MessageService			messageService;
	@Autowired
	private ActorService			actorService;


	// Constructors -----------------------------------------------------------
	public MessageFolderService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------
	public MessageFolder create() {
		MessageFolder messagefolder = new MessageFolder();
		Actor actor;
		List<Message> messages = new ArrayList<>();
		actor = this.actorService.findPrincipal();
		Collection<MessageFolder> messagesFolders = this.messageFolderRepository.ActorFolders(actor.getId());
		messagesFolders.add(messagefolder);
		messagefolder.setMessages(messages);
		return messagefolder;
	}
	// Other business methods -------------------------------------------------

}
