
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curricula;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CurriculaServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private CurriculaService		curriculaService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private PersonalRecordService	personalRecordService;


	//TODO

	@Test
	public void testCreate() {
		Curricula curricula;
		curricula = this.curriculaService.create();
		Assert.notNull(curricula);
	}

	@Test
	public void testSave() {
		Curricula curricula;
		curricula = this.curriculaService.create();

		PersonalRecord personalRecord = this.personalRecordService.create();
		personalRecord.setFullName("personalRecord 8");
		personalRecord.setEmail("dany@gmail.com");
		personalRecord.setLinkedProfile("https://www.example.com");
		personalRecord.setPhoto("https://www.example.com");
		personalRecord.setPhone("+34(578)1239");

		PersonalRecord newPersonalRecord = this.personalRecordService.save(personalRecord);
		curricula.setPersonalRecord(newPersonalRecord);

		curricula.setTicker("041117-FFFF");

		this.curriculaService.save(curricula);

	}
	@Test
	public void testFindAll() {
		Collection<Curricula> curriculas;
		curriculas = this.curriculaService.findAll();
		Assert.notNull(curriculas);
	}

	@Test
	public void testFindOne() {
		Curricula curricula;

		curricula = this.curriculaService.findOne(6190);
		Assert.notNull(curricula);
	}

	@Test
	public void testDelete() {
		Curricula curricula;
		curricula = this.curriculaService.findOne(6190);

		this.curriculaService.delete(curricula);
	}
	@Test
	public void testUpdate() {
		Curricula curriculaModify = this.curriculaService.findOne(6190);

		this.curriculaService.update(curriculaModify);
	}
}
