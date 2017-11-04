
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationForRepository;
import domain.ApplicationFor;

@Service
@Transactional
public class ApplicationForService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ApplicationForRepository	applicationForRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ApplicationForService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ApplicationFor create() {
		ApplicationFor result;
		String status;
		Collection<String> comments;

		result = new ApplicationFor();
		status = "PENDING";
		comments = new ArrayList<String>();

		result.setStatus(status);
		result.setComments(comments);

		return result;
	}
	public Collection<ApplicationFor> findAll() {
		Collection<ApplicationFor> result;

		Assert.notNull(this.applicationForRepository);
		result = this.applicationForRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public ApplicationFor findOne(final int applicationForId) {
		ApplicationFor result;

		result = this.applicationForRepository.findOne(applicationForId);

		return result;
	}

	public ApplicationFor save(final ApplicationFor applicationFor) {
		assert applicationFor != null;

		ApplicationFor result;

		result = this.applicationForRepository.save(applicationFor);

		return result;
	}

	public void delete(final ApplicationFor applicationFor) {
		assert applicationFor != null;
		assert applicationFor.getId() != 0;

		Assert.isTrue(this.applicationForRepository.exists(applicationFor.getId()));

		this.applicationForRepository.delete(applicationFor);
	}
}
