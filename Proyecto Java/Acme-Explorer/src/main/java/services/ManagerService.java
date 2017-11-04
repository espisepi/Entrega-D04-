
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ManagerRepository	managerRepository;


	// Constructors-------------------------------------------------------

	public ManagerService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Manager create() {

		Manager result;

		result = new Manager();

		return result;

	}

	public Collection<Manager> findAll() {

		Collection<Manager> result;

		result = this.managerRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Manager findOne(int managerId) {

		Manager result;

		result = this.managerRepository.findOne(managerId);

		return result;
	}

	public Manager save(Manager manager) {

		// TODO : hacer
		return null;
	}

	public void delete(Manager manager) {

		// TODO: HACER

	}
}
