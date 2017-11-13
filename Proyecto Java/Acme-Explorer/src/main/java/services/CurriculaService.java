
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
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

		this.rangerService.checkPrincipal();

		Curricula curricula;
		List<ProfessionalRecord> professionalRecords;
		List<MiscellaneousRecord> miscellaneousRecords;
		List<EndorserRecord> endorserRecords;
		List<EducationRecord> educationRecords;

		professionalRecords = new ArrayList<ProfessionalRecord>();
		miscellaneousRecords = new ArrayList<MiscellaneousRecord>();
		endorserRecords = new ArrayList<EndorserRecord>();
		educationRecords = new ArrayList<EducationRecord>();
		curricula = new Curricula();

		curricula.setEducationRecords(educationRecords);
		curricula.setEndorserRecords(endorserRecords);
		curricula.setMiscellaneousRecords(miscellaneousRecords);
		curricula.setProfessionalRecords(professionalRecords);

		return curricula;

	}
	public Curricula save(Curricula curricula) {

		Ranger ranger;
		Curricula newCurricula;

		Assert.notNull(curricula);
		Assert.notNull(curricula.getPersonalRecord());

		ranger = this.rangerService.findByPrincipal();
		newCurricula = this.curriculaRepository.save(curricula);
		ranger.setCurricula(newCurricula);

		Assert.notNull(newCurricula);

		return newCurricula;
	}

	public Curricula update(Integer curriculaId, PersonalRecord personalRecord, Collection<ProfessionalRecord> professionalRecords, Collection<EducationRecord> educationRecords, Collection<EndorserRecord> endorserRecords,
		Collection<MiscellaneousRecord> miscellaneousRecords) {

		Ranger ranger;
		Curricula curriculaFromRanger;
		Curricula updateCurricula;
		PersonalRecord newPersonalRecord;
		Curricula curriculaBeforeUpdate;

		Assert.notNull(personalRecord);
		Assert.notNull(this.curriculaRepository.findOne(curriculaId));

		//Tengo que comprobar que el que quiera modificar esa curricula es su propio ranger
		this.rangerService.checkPrincipal();

		ranger = this.rangerService.findByPrincipal();
		curriculaFromRanger = this.findCurriculaFromRanger(ranger.getId());

		Assert.isTrue(curriculaFromRanger.getId() == (curriculaId));

		updateCurricula = new Curricula();
		newPersonalRecord = this.personalRecordService.save(personalRecord);

		updateCurricula.setEducationRecords(educationRecords);
		updateCurricula.setEndorserRecords(endorserRecords);
		updateCurricula.setMiscellaneousRecords(miscellaneousRecords);
		updateCurricula.setPersonalRecord(newPersonalRecord);
		updateCurricula.setProfessionalRecords(professionalRecords);
		updateCurricula.setId(curriculaId);
		updateCurricula.setTicker(this.curriculaRepository.findOne(curriculaId).getTicker());

		Assert.isTrue(updateCurricula.getTicker() == this.curriculaRepository.findOne(curriculaId).getTicker());
		curriculaBeforeUpdate = this.curriculaRepository.save(updateCurricula);

		Assert.notNull(curriculaBeforeUpdate);

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
		Assert.notNull(curriculaId);
		Assert.isTrue(curriculaId != 0);

		Curricula curricula;

		curricula = this.curriculaRepository.findOne(curriculaId);

		return curricula;
	}

	// Other methods Bussiness --------------------------------------------------------

	public Curricula findCurriculaFromRanger(int rangerId) {

		Curricula curricula;

		curricula = this.curriculaRepository.findCurriculaFromRanger(rangerId);

		return curricula;
	}
}
