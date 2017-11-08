
package services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
public class CurriculaServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private CurriculaService			curriculaService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private PersonalRecordService		personalRecordService;

	@Autowired
	private ProfessionalRecordService	professionalRecordService;

	@Autowired
	private EducationRecordService		educationRecordService;

	@Autowired
	private EndorserRecordService		endorserRecordService;

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	//TODO

	//	@Test
	//	public void testCreate() {
	//		this.authenticate("ranger1");
	//		Curricula curricula;
	//		curricula = this.curriculaService.create();
	//		Assert.notNull(curricula);
	//		this.authenticate(null);
	//	}
	//
	//	@Test
	//	public void testSave() {
	//		this.authenticate("ranger1");
	//		Curricula curricula;
	//		curricula = this.curriculaService.create();
	//
	//		PersonalRecord personalRecord = this.personalRecordService.create();
	//		personalRecord.setFullName("personalRecord 8");
	//		personalRecord.setEmail("dany@gmail.com");
	//		personalRecord.setLinkedProfile("https://www.example.com");
	//		personalRecord.setPhoto("https://www.example.com");
	//		personalRecord.setPhone("+34(578)1239");
	//
	//		PersonalRecord newPersonalRecord = this.personalRecordService.save(personalRecord);
	//		curricula.setPersonalRecord(newPersonalRecord);
	//
	//		curricula.setTicker("041117-FFFF");
	//
	//		this.curriculaService.save(curricula);
	//		this.authenticate(null);
	//
	//	}
	//	@Test
	//	public void testFindAll() {
	//		Collection<Curricula> curriculas;
	//		curriculas = this.curriculaService.findAll();
	//		Assert.notNull(curriculas);
	//	}
	//
	//	@Test
	//	public void testFindOne() {
	//		Curricula curricula;
	//
	//		curricula = this.curriculaService.findOne(6190);
	//		Assert.notNull(curricula);
	//	}
	//
	//	@Test
	//	public void testDelete() {
	//		Curricula curricula;
	//		curricula = this.curriculaService.findOne(6190);
	//
	//		this.curriculaService.delete(curricula);
	//	}
	@Test
	public void testUpdate() {
		this.authenticate("ranger2");
		PersonalRecord personalRecord1 = this.personalRecordService.create();
		personalRecord1.setEmail("modify@hotmail.com");
		personalRecord1.setFullName("modify1");
		personalRecord1.setLinkedProfile("https://www.modify.com");
		personalRecord1.setPhone("+34(578)1294");
		personalRecord1.setPhoto("https://www.photo.com");

		this.personalRecordService.save(personalRecord1);

		//		ProfessionalRecord professionalRecord1 = this.professionalRecordService.create();
		//		EducationRecord educationRecord1 = this.educationRecordService.create();
		//		EndorserRecord endorserRecord1 = this.endorserRecordService.create();
		//		MiscellaneousRecord miscellaneousRecord1 = this.miscellaneousRecordService.create();

		List<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		List<EducationRecord> educationRecords = new ArrayList<EducationRecord>();
		List<EndorserRecord> endorserRecords = new ArrayList<EndorserRecord>();
		List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<MiscellaneousRecord>();

		this.curriculaService.update(6191, personalRecord1, professionalRecords, educationRecords, endorserRecords, miscellaneousRecords);
		this.authenticate(null);
	}
}
