
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;
import domain.MessageFolder;

@Service
@Transactional
public class MessageService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private MessageRepository	messageRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------
	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Message create() {
		final Actor sender = this.actorService.findPrincipal();
		Message message;
		message = new Message();
		message.setMoment(new Date());
		message.setSender(sender);
		return message;
	}
	public Message findOne(final int messageId) {
		Assert.isTrue(messageId != 0);

		Message result;

		result = this.messageRepository.findOne(messageId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Message> findAll() {
		Collection<Message> result;
		result = this.messageRepository.findAll();
		return result;

	}

	public Message Save(final Message message) {
		//Guarda el mensaje en la carpeta outbox
		Collection<MessageFolder> m;
		final Actor sender;
		Message result;
		result = message;
		Date current;
		current = new Date(System.currentTimeMillis() - 1000);
		result.setMoment(current);
		m = message.getSender().getMessagesFolders();
		for (final MessageFolder folder : m)
			if (folder.getName().equals("out box")) {
				message.setMessageFolder(folder);
				folder.getMessages().add(result);
				this.messageRepository.save(message);
				break;
			}

		return result;
	}
	public void delete(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		this.messageRepository.delete(message);
	}
	// Other business methods -------------------------------------------------
	public boolean MessageisSpam(final Message message) {
		//Comprueba si un mensaje es spam
		boolean bool = false;
		final Collection<String> words = new ArrayList<String>();
		Collection<String> spams;

		words.add(message.getBody());
		words.add(message.getSubject());

		spams = this.SpamWord();

		for (final String w : words)
			for (final String spam : spams) {
				final int i = w.indexOf(spam);
				if (i != -1)
					bool = true;
			}
		return bool;

	}
	public Collection<String> SpamWord() {
		//devuelve la collecion de palabras spam
		Collection<String> res;
		res = this.messageRepository.SpamWord();
		return res;

	}
	public Collection<Message> recipientAllByActor(final int idActor) {
		//Todos los mensajes recibidos de un actor
		Collection<Message> res;
		res = this.messageRepository.recipientAllByActor(idActor);
		return res;

	}
	public Collection<Message> senderAllByActor(final int idActor) {
		//Todos los mensajes enviados de un actor
		Collection<Message> res;
		res = this.messageRepository.senderAllByActor(idActor);
		return res;

	}

	public void ChangeMessageOfFolder(final Message message, final MessageFolder folder) {

		if (!message.getMessageFolder().equals(folder))
			message.setMessageFolder(folder);
	}

}
