
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RangerRepository;
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

		// TODO : hacer
		return null;
	}

	public void delete(Ranger ranger) {

		// TODO: HACER

	}

}
