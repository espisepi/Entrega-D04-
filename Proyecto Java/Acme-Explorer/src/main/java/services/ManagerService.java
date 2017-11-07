
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
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

		Assert.notNull(manager);

		Manager result;

		result = this.managerRepository.save(manager);

		return result;
	}

	public void delete(Manager manager) {

		Assert.notNull(manager);
		Assert.isTrue(manager.getId() != 0);

		this.managerRepository.delete(manager);

	}

	// Other business methods----------------------------------

	public Manager findByPrincipal() {

		Manager result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.managerRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public void checkPrincipal() {

		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("MANAGER");

		Assert.isTrue(authorities.contains(auth));
	}

}
