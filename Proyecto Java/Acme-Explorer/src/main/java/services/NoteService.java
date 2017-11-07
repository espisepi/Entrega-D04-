
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Auditor;
import domain.Note;
import domain.Trip;

@Service
@Transactional
public class NoteService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private NoteRepository	noteRepository;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public NoteService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Note create() {
		Note result;
		Auditor auditor;
		Trip trip;
		Date createdMoment;
		result = new Note();
		auditor = new Auditor();
		trip = new Trip();
		createdMoment = new Date();

		result.setCreatedMoment(createdMoment);
		result.setAuditor(auditor);
		result.setTrip(trip);
		return result;
	}

	public Note findOne(final int noteId) {
		Assert.isTrue(noteId != 0);
		Note result;
		result = this.noteRepository.findOne(noteId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Note> findAll() {
		Collection<Note> result;
		result = this.noteRepository.findAll();
		return result;
	}

	public Note save(Note note) {
		Assert.notNull(note);
		Note result;
		result = this.noteRepository.save(note);
		Date createdMoment;
		createdMoment = new Date(System.currentTimeMillis() - 1000);
		result.setCreatedMoment(createdMoment);
		Assert.notNull(result);
		return result;
	}

	//dijimos que la nota iba sin el m�todo delete.
	//	public void delete(final Note note) {
	//		assert note != null;
	//		assert note.getId() != 0;
	//		Assert.isTrue(this.noteRepository.exists(note.getId()));
	//		this.noteRepository.delete(note);
	//	}

	// Other business methods------------------------------------------------------

}
