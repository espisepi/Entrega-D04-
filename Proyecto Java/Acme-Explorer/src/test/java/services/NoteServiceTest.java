
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Auditor;
import domain.Note;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private NoteService		noteService;

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private TripService		tripService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		this.authenticate("auditor4");
		Note result;
		result = this.noteService.create();
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Note> result = this.noteService.findAll();
		Assert.notEmpty(result);
	}

	@Test
	public void testSave() {
		this.authenticate("auditor4");
		Note note;
		Auditor auditor;
		Trip trip;
		String body;

		note = this.noteService.create();
		trip = this.tripService.findAll().iterator().next();
		auditor = this.auditorService.findAll().iterator().next();
		body = "h";

		note.setAuditor(auditor);
		note.setTrip(trip);
		note.setRemark(5);
		note.setBody(body);

		note = this.noteService.save(note);
	}

	@Test
	public void testfindOne() {
		Collection<Note> notes;
		Note note;

		notes = this.noteService.findAll();
		Assert.notNull(notes);
		Assert.notEmpty(notes);

		note = this.noteService.findOne(notes.iterator().next().getId());
		Assert.notNull(note);
	}

	@Test
	public void testReplyANote() {
		this.authenticate("auditor4");
		Note note;
		String reply;
		Auditor auditor;
		Trip trip;

		note = this.noteService.create();
		auditor = this.auditorService.findAll().iterator().next();
		trip = this.tripService.findAll().iterator().next();

		note.setTrip(trip);
		note.setRemark(6);
		note.setAuditor(auditor);
		note.setBody("este es el body");

		note = this.noteService.save(note);
		this.unauthenticate();
		this.authenticate("manager1");

		reply = "reply";
		this.noteService.replyANote(note, reply);

	}

}
