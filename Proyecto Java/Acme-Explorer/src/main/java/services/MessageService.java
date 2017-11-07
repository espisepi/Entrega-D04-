
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
		Actor sender = this.actorService.findPrincipal();
		Message message;
		message = new Message();
		message.setMoment(new Date());
		message.setSender(sender);
		return message;
	}
	public Message findOne(int messageId) {
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

	public Message Save(Message message) {
		//TODO
		Collection<MessageFolder> m;
		Actor sender = this.actorService.findPrincipal();
		Message result;
		result = message;
		Date current;
		current = new Date(System.currentTimeMillis() - 1000);
		result.setMoment(current);
		message.setSender(sender);
		m = sender.getMessagesFolders();
		for (MessageFolder folder : m)
			if (folder.getName().equals("out box"))
				folder.getMessages().add(message);
		this.messageRepository.saveAndFlush(result);
		return result;
	}
	public void delete(Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		this.messageRepository.delete(message);
	}
	// Other business methods -------------------------------------------------
	public boolean MessageisSpam(Message message) {
		//Comprueba si un mensaje es spam
		boolean bool = false;
		Collection<String> words = new ArrayList<String>();
		Collection<String> spams;

		words.add(message.getBody());
		words.add(message.getSubject());

		spams = this.SpamWord();

		for (String w : words)
			for (String spam : spams) {
				int i = w.indexOf(spam);
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
	public Collection<Message> recipientAllByActor(int idActor) {
		//Todos los mensajes recibidos de un actor
		Collection<Message> res;
		res = this.messageRepository.recipientAllByActor(idActor);
		return res;

	}
	public Collection<Message> senderAllByActor(int idActor) {
		//Todos los mensajes enviados de un actor
		Collection<Message> res;
		res = this.messageRepository.senderAllByActor(idActor);
		return res;

	}

	public void ChangeMessageOfFolder(Message message, MessageFolder folder) {

		if (!message.getMessageFolder().equals(folder))
			message.setMessageFolder(folder);
	}

}
