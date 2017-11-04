
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO: Auto-generated method stub
		return null;
	}

	public Collection<Manager> findAll() {
		// TODO: hacer
		return null;
	}

	public Manager findOne(int managerId) {

		// TODO: HACER
		return null;
	}

	public Manager save(Manager manager) {

		// TODO : hacer
		return null;
	}

	public void delete(Manager manager) {

		// TODO: HACER

	}
}
