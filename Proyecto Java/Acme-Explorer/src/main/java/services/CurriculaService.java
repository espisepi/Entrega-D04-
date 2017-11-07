
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
import domain.ProfessionalRecord;

@Service
@Transactional
public class CurriculaService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CurriculaRepository	curriculaRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private RangerService		rangerService;


	// Constructors-------------------------------------------------------

	public CurriculaService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Collection<Curricula> findAll() {
		Collection<Curricula> curriculas;

		curriculas = this.curriculaRepository.findAll();
		Assert.notNull(curriculas);

		return curriculas;
	}

	public Curricula findOne(int curriculaId) {
		Curricula curricula;

		curricula = this.curriculaRepository.findOne(curriculaId);
		Assert.notNull(curricula);

		return curricula;
	}

	public Curricula save(Curricula curricula) {

		Assert.isTrue(curricula.getId() == 0);
		Curricula newCurricula;
		Assert.notNull(curricula);

		Assert.notNull(curricula.getPersonalRecord());

		newCurricula = this.curriculaRepository.saveAndFlush(curricula);

		Assert.notNull(newCurricula);

		return newCurricula;
	}

	public Curricula update(Curricula curricula) {

		Assert.notNull(curricula);
		Assert.isTrue(curricula.getId() != 0);

		Curricula curriculaBD = this.curriculaRepository.findOne(curricula.getId());
		String tike = curricula.getTicker();
		Assert.isTrue(curriculaBD.getTicker() == curricula.getTicker());

		Curricula newCurricula = this.curriculaRepository.saveAndFlush(curricula);

		return newCurricula;
	}
	public Curricula create() {
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

	public void delete(Curricula curricula) {
		Assert.notNull(curricula);
		Assert.notNull(this.curriculaRepository.findOne(curricula.getId()));

		this.curriculaRepository.delete(curricula);

	}

}
