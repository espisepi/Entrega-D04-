
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalClassRepository;
import domain.Explorer;
import domain.Manager;
import domain.SurvivalClass;

@Service
@Transactional
public class SurvivalClassService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SurvivalClassRepository	survivalClassRecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ManagerService			managerService;


	// Constructors-------------------------------------------------------

	public SurvivalClassService() {

		super();
	}

	// Simple CRUD methods------------------------------------------------

	public SurvivalClass create() {

		Manager manager;
		SurvivalClass result;
		Collection<Explorer> explorers;
		Date organisedMoment = new Date();

		manager = this.managerService.findByPrincipal();
		explorers = new ArrayList<>();

		result = new SurvivalClass();
		result.setManager(manager);
		result.setExplorers(explorers);
		result.setOrganisedMoment(organisedMoment);

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

		Assert.notNull(result);

		return result;
	}

	public SurvivalClass save(SurvivalClass survivalClass) {

		Assert.notNull(survivalClass);

		SurvivalClass result;

		if (survivalClass.getId() == 0) {
			Date organisedMoment = new Date();
			survivalClass.setOrganisedMoment(organisedMoment);
		}

		result = this.survivalClassRecordRepository.save(survivalClass);

		return result;
	}
	public void delete(SurvivalClass survivalClass) {

		Assert.notNull(survivalClass);
		Assert.isTrue(survivalClass.getId() != 0);

		this.survivalClassRecordRepository.delete(survivalClass);

	}
}
