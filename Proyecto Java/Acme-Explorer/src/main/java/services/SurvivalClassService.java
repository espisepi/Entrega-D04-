
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO: Auto-generated method stub
		return null;
	}

	public Collection<SurvivalClass> findAll() {
		// TODO: hacer
		return null;
	}

	public SurvivalClass findOne(int survivalClassId) {

		// TODO: HACER
		return null;
	}

	public SurvivalClass save(SurvivalClass survivalClass) {

		// TODO : hacer
		return null;
	}

	public void delete(SurvivalClass survivalClass) {

		// TODO: HACER

	}

}
