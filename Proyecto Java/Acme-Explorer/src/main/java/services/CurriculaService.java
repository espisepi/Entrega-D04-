
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Curricula;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
import domain.Ranger;

@Service
@Transactional
public class CurriculaService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CurriculaRepository		curriculaRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private RangerService			rangerService;

	@Autowired
	private PersonalRecordService	personalRecordService;


	// Constructors-------------------------------------------------------

	public CurriculaService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Curricula create() {

		this.checkPrincipal();

		Curricula curricula;

		curricula = new Curricula();
		List<ProfessionalRecord> professionalRecords = new ArrayList<>();
		List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<>();
		List<EndorserRecord> endorserRecords = new ArrayList<>();
		List<EducationRecord> educationRecords = new ArrayList<>();

		curricula.setEducationRecords(educationRecords);
		curricula.setEndorserRecords(endorserRecords);
		curricula.setMiscellaneousRecords(miscellaneousRecords);
		curricula.setProfessionalRecords(professionalRecords);

		return curricula;

	}

	public Curricula save(Curricula curricula) {

		Ranger ranger;
		ranger = this.rangerService.findByPrincipal();

		Assert.isTrue(curricula.getId() == 0);
		Curricula newCurricula;
		Assert.notNull(curricula);

		Assert.notNull(curricula.getPersonalRecord());

		newCurricula = this.curriculaRepository.saveAndFlush(curricula);
		ranger.setCurricula(newCurricula);

		Assert.notNull(newCurricula);

		return newCurricula;
	}

	public Curricula update(Integer curriculaId, PersonalRecord personalRecord, Collection<ProfessionalRecord> professionalRecords, Collection<EducationRecord> educationRecords, Collection<EndorserRecord> endorserRecords,
		Collection<MiscellaneousRecord> miscellaneousRecords) {

		Assert.notNull(personalRecord);
		Assert.notNull(this.curriculaRepository.findOne(curriculaId));

		//Tengo que comprobar que el que quiera modificar esa curricula es su propio ranger
		this.checkPrincipal();
		Ranger ranger = this.rangerService.findByPrincipal();
		Curricula curriculaFromRanger = this.findCurriculaFromRanger(ranger.getId());
		Assert.isTrue(curriculaFromRanger.getId() == (curriculaId));

		Curricula updateCurricula;
		updateCurricula = new Curricula();

		PersonalRecord newPersonalRecord = this.personalRecordService.save(personalRecord);

		updateCurricula.setEducationRecords(educationRecords);
		updateCurricula.setEndorserRecords(endorserRecords);
		updateCurricula.setMiscellaneousRecords(miscellaneousRecords);
		updateCurricula.setPersonalRecord(newPersonalRecord);
		updateCurricula.setProfessionalRecords(professionalRecords);
		updateCurricula.setId(curriculaId);
		updateCurricula.setTicker(this.curriculaRepository.findOne(curriculaId).getTicker());

		//		Curricula curriculaToModify = this.curriculaRepository.findOne(curriculaId);
		//		curriculaToModify.setPersonalRecord(newPersonalRecord);
		//		curriculaToModify.setProfessionalRecords(professionalRecords);
		//		curriculaToModify.setEducationRecords(educationRecords);
		//		curriculaToModify.setEndorserRecords(endorserRecords);
		//		curriculaToModify.setMiscellaneousRecords(miscellaneousRecords);

		Assert.isTrue(updateCurricula.getTicker() == this.curriculaRepository.findOne(curriculaId).getTicker());
		this.curriculaRepository.saveAndFlush(updateCurricula);
		return this.curriculaRepository.findOne(curriculaId);

	}
	public void delete(Curricula curricula) {

		Ranger rangerWithThisCurricula;
		rangerWithThisCurricula = this.curriculaRepository.rangerWithThisCurricula(curricula.getId());
		rangerWithThisCurricula.setCurricula(null);
		Assert.notNull(curricula);
		Assert.notNull(this.curriculaRepository.findOne(curricula.getId()));

		this.curriculaRepository.delete(curricula);

	}

	public Collection<Curricula> findAll() {
		Collection<Curricula> curriculas;

		curriculas = this.curriculaRepository.findAll();
		Assert.notNull(curriculas);

		return curriculas;
	}

	public Curricula findOne(int curriculaId) {
		Curricula curricula;

		curricula = this.curriculaRepository.findOne(curriculaId);

		return curricula;
	}

	// Other methods Bussiness --------------------------------------------------------
	public void checkPrincipal() {

		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("RANGER");

		Assert.isTrue(authorities.contains(auth));
	}

	public Curricula findCurriculaFromRanger(int rangerId) {

		Curricula curricula = this.curriculaRepository.findCurriculaFromRanger(rangerId);
		return curricula;
	}

}
