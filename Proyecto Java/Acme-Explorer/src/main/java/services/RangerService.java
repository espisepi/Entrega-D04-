
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO: Auto-generated method stub
		return null;
	}

	public Collection<Ranger> findAll() {
		// TODO: hacer
		return null;
	}

	public Ranger findOne(int RangerId) {

		// TODO: HACER
		return null;
	}

	public Ranger save(Ranger ranger) {

		// TODO : hacer
		return null;
	}

	public void delete(Ranger ranger) {

		// TODO: HACER

	}

}
