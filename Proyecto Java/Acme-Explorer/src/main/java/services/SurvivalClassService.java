
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalClassRepository;
import domain.SurvivalClass;

@Service
@Transactional
public class SurvivalClassService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SurvivalClassRepository	survivalClassRecordRepository;


	// Constructors-------------------------------------------------------

	public SurvivalClassService() {

		super();
	}

	// Simple CRUD methods------------------------------------------------

	public SurvivalClass create() {

		SurvivalClass result;

		result = new SurvivalClass();

		return result;
	}

	public Collection<SurvivalClass> findAll() {

		Collection<SurvivalClass> result;

		result = this.survivalClassRecordRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public SurvivalClass findOne(int survivalClassId) {

		SurvivalClass result;

		result = this.survivalClassRecordRepository.findOne(survivalClassId);

		return result;
	}

	public SurvivalClass save(SurvivalClass survivalClass) {

		// TODO : hacer
		return null;
	}

	public void delete(SurvivalClass survivalClass) {

		// TODO: HACER

	}

}
