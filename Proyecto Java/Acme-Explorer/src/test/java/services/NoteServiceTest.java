
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

		super.unauthenticate();
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
		Trip trip1;
		String body;

		note = this.noteService.create();
		trip1 = this.tripService.findOne(super.getEntityId("trip1"));

		body = "note's test body";

		note.setTrip(trip1);
		note.setRemark(5);
		note.setBody(body);

		auditor = this.auditorService.findByPrincipal();
		Assert.notNull(auditor);
		note = this.noteService.save(note);

		//	Assert.isTrue(auditor.getNotes().contains(note));
		//	Assert.isTrue(trip1.getNotes().contains(note));
		super.unauthenticate();
	}

	@Test
	public void testfindOne() {
		Note note;
		note = this.noteService.findOne(super.getEntityId("note1"));
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
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		trip = this.tripService.findOne(super.getEntityId("trip1"));

		note.setTrip(trip);
		note.setRemark(6);
		note.setAuditor(auditor);
		note.setBody("this is the body");

		note = this.noteService.save(note);
		this.unauthenticate();
		this.authenticate("manager1");

		reply = "I am the manager and I am writing this reply";
		this.noteService.replyANote(note, reply);

	}

}
