
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import domain.Curricula;

@Service
@Transactional
public class CurriculaService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CurriculaRepository	curriculaRepository;


	// Supporting services ----------------------------------------------------

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
		Curricula newCurricula;
		Assert.notNull(curricula);

		newCurricula = this.curriculaRepository.saveAndFlush(curricula);
		Assert.notNull(newCurricula);

		return newCurricula;
	}

	public Curricula update(Curricula curricula) {
		Curricula newCurricula;
		Assert.notNull(curricula);

		newCurricula = this.curriculaRepository.saveAndFlush(curricula);

		return newCurricula;
	}
	public Curricula create() {
		Curricula curricula;
		curricula = new Curricula();

		return curricula;

	}

	public void delete(Curricula curricula) {

		//TODO: Mirar como se borra una curricula y lo que necesita para que se borre
		Assert.notNull(curricula);

		this.curriculaRepository.delete(curricula);

	}

}
