
package service;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.CurriculaService;
import services.PersonalRecordService;
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
	public void testCreatePositive() {
		Curricula curricula;
		curricula = this.curriculaService.create();
		Assert.notNull(curricula);
	}

	@Test
	public void testSavePositive() {
		Curricula curricula;
		curricula = this.curriculaService.create();

		PersonalRecord personalRecord = this.personalRecordService.create();
		personalRecord.setFullName("personalRecord 8");
		personalRecord.setEmail("dany@gmail.com");
		personalRecord.setLinkedProfile("https://www.example.com");
		personalRecord.setPhoto("https://www.example.com");
		personalRecord.setPhone("+34(578)1239");

		curricula.setPersonalRecord(personalRecord);
		Assert.notNull(curricula);

		this.curriculaService.save(curricula);

	}
	@Test
	public void testFindAllPositive() {
		Collection<Curricula> curriculas;
		curriculas = this.curriculaService.findAll();
		Assert.notEmpty(curriculas);
	}

	@Test
	public void testFindOnePositive() {
		Curricula curricula;
		curricula = this.curriculaService.findOne(6248);
		Assert.notNull(curricula);
	}
}
