
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationSystemRepository;
import domain.ConfigurationSystem;

@Service
@Transactional
public class ConfigurationSystemService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ConfigurationSystemRepository	configurationSystemRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ConfigurationSystemService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<ConfigurationSystem> findAll() {
		Collection<ConfigurationSystem> result;

		Assert.notNull(this.configurationSystemRepository);
		result = this.configurationSystemRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public ConfigurationSystem findOne() {
		ConfigurationSystem res;

		res = this.findAll().iterator().next();

		return res;

	}

	public ConfigurationSystem findOne(final int configurationSystemId) {
		ConfigurationSystem result;

		result = this.configurationSystemRepository.findOne(configurationSystemId);

		return result;
	}

	public ConfigurationSystem save(final ConfigurationSystem configurationSystem) {
		assert configurationSystem != null;

		ConfigurationSystem result;

		result = this.configurationSystemRepository.save(configurationSystem);

		return result;
	}

	public void delete(final ConfigurationSystem configurationSystem) {
		assert configurationSystem != null;
		assert configurationSystem.getId() != 0;

		Assert.isTrue(this.configurationSystemRepository.exists(configurationSystem.getId()));

		this.configurationSystemRepository.delete(configurationSystem);
	}

	// Other business methods -------------------------------------------------
}
