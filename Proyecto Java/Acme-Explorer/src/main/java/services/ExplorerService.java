
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExplorerRepository;
import security.Authority;
import security.UserAccount;
import domain.ApplicationFor;
import domain.ContactEmergency;
import domain.Explorer;
import domain.MessageFolder;
import domain.SocialIdentity;
import domain.Story;

@Service
@Transactional
public class ExplorerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ExplorerRepository	explorerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ExplorerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Explorer create() {
		Explorer result;
		UserAccount userAccount;
		Authority authority;
		final Collection<SocialIdentity> socialIdentities;
		final Collection<MessageFolder> messagesFolders;
		final Collection<Story> stories;
		final Collection<ApplicationFor> applicationsFor;
		final Collection<ContactEmergency> contactsEmergency;

		result = new Explorer();
		userAccount = new UserAccount();
		authority = new Authority();
		messagesFolders = new ArrayList<MessageFolder>();
		socialIdentities = new ArrayList<SocialIdentity>();
		stories = new ArrayList<Story>();
		applicationsFor = new ArrayList<ApplicationFor>();
		contactsEmergency = new ArrayList<ContactEmergency>();

		result.setSocialIdentities(socialIdentities);
		result.setMessagesFolders(messagesFolders);

		authority.setAuthority(Authority.EXPLORER);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		result.setStories(stories);
		result.setApplicationsFor(applicationsFor);
		result.setContactsEmergency(contactsEmergency);

		return result;
	}

	public Collection<Explorer> findAll() {
		Collection<Explorer> result;

		Assert.notNull(this.explorerRepository);
		result = this.explorerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Explorer findOne(final int explorerId) {
		Explorer result;

		result = this.explorerRepository.findOne(explorerId);

		return result;
	}

	public Explorer save(final Explorer explorer) {
		Explorer newExplorer;
		Assert.notNull(explorer);

		String password = explorer.getUserAccount().getPassword();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		explorer.getUserAccount().setPassword(password);

		newExplorer = this.explorerRepository.save(explorer);
		//folderService.createDefaultFolders(newExplorer);
		return newExplorer;
	}

	public void delete(final Explorer explorer) {
		assert explorer != null;
		assert explorer.getId() != 0;

		Assert.isTrue(this.explorerRepository.exists(explorer.getId()));

		this.explorerRepository.delete(explorer);
	}

	// Other business methods -------------------------------------------------
}
