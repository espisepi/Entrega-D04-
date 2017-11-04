
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RangerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Ranger;

@Service
@Transactional
public class RangerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RangerRepository	RangerRepository;


	// Constructors-------------------------------------------------------

	public RangerService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Ranger create() {

		Ranger result;

		result = new Ranger();

		return result;
	}

	public Collection<Ranger> findAll() {

		Collection<Ranger> result;

		result = this.RangerRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Ranger findOne(int RangerId) {

		Assert.isTrue(RangerId != 0);

		Ranger result;

		result = this.RangerRepository.findOne(RangerId);

		Assert.notNull(result);

		return result;
	}

	public Ranger save(Ranger ranger) {

		Assert.notNull(ranger);

		Ranger result;

		result = this.RangerRepository.save(ranger);

		return result;
	}

	public void delete(Ranger ranger) {

		Assert.notNull(ranger);
		Assert.isTrue(ranger.getId() != 0);

		this.RangerRepository.delete(ranger);

	}

	public void checkPrincipal() {

		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("ADMIN");

		Assert.isTrue(authorities.contains(auth));
	}

}
