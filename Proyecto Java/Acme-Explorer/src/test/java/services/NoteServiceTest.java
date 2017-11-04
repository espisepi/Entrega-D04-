
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private NoteService	noteService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		this.authenticate("auditor 1");
		Note res;
		res = this.noteService.create();
		Assert.notNull(res);

	}

}
