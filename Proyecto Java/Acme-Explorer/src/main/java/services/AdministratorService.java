
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.MessageFolder;
import domain.SocialIdentity;
import domain.Trip;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageFolderService	messageFolderService;


	// Constructors-------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Administrator create() {
		Administrator result;
		UserAccount userAccount;
		Authority authority;
		Collection<SocialIdentity> socialIdentities;
		Collection<MessageFolder> messagesFolders;

		result = new Administrator();
		userAccount = new UserAccount();
		authority = new Authority();
		messagesFolders = new ArrayList<MessageFolder>();
		socialIdentities = new ArrayList<SocialIdentity>();

		messagesFolders = this.messageFolderService.createDefaultFolders();

		authority.setAuthority(Authority.ADMINISTRATOR);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);
		result.setSocialIdentities(socialIdentities);
		result.setMessagesFolders(messagesFolders);

		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;
		result = this.administratorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator result;
		result = this.administratorRepository.findOne(administratorId);
		Assert.notNull(result);
		return result;
	}

	public Administrator save(Administrator administrator) {
		Assert.notNull(administrator);
		Administrator result;
		result = this.administratorRepository.save(administrator);
		return result;
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		this.administratorRepository.delete(administrator);
	}

	// Other business methods------------------------------------------------------

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		result = this.administratorRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public void checkPrincipal() {

		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("ADMINISTRATOR");

		Assert.isTrue(authorities.contains(auth));
	}

	public Double[] findMaxMinAvgStddevOfTheNumOfApplicationsPerTrip() {
		Double[] result;
		result = this.administratorRepository.findMaxMinAvgStddevOfTheNumOfApplicationsPerTrip();
		Assert.notNull(result);
		return result;
	}

	public Double[] findMaxMinAvgStddevOfTheNumOfTripsPerManager() {
		Double[] result;
		result = this.administratorRepository.findMaxMinAvgStddevOfTheNumOfTripsPerManager();
		Assert.notNull(result);
		return result;
	}

	public Double[] findMaxMinAvgStddevOfThePriceOfTheTrips() {
		Double[] result;
		result = this.administratorRepository.findMaxMinAvgStddevOfThePriceOfTheTrips();
		Assert.notNull(result);
		return result;
	}

	public Double[] findMaxMinAvgStddevOfTheNumTripsPerRanger() {
		Double[] result;
		result = this.administratorRepository.findMaxMinAvgStddevOfTheNumTripsPerRanger();
		Assert.notNull(result);
		return result;
	}

	public Double findRatioOfApplicationsPending() {
		Double result;
		result = this.administratorRepository.findRatioOfApplicationsPending();
		return result;
	}

	public Double findRatioOfApplicationsDue() {
		Double result;
		result = this.administratorRepository.findRatioOfApplicationsDue();
		return result;
	}

	public Double findRatioOfApplicationsAccepted() {
		Double result;
		result = this.administratorRepository.findRatioOfApplicationsAccepted();
		return result;
	}

	public Double findRatioOfApplicationsCancelled() {
		Double result;
		result = this.administratorRepository.findRatioOfApplicationsCancelled();
		return result;
	}

	public Double findRatioOfTheTripsCancelledvsTripsOrganised() {
		Double result;
		result = this.administratorRepository.findRatioOfTheTripsCancelledvsTripsOrganised();
		return result;
	}

	public Collection<Trip> findTrips10porcentMoreApplicationsThanAvg() {
		Collection<Trip> result;
		result = this.administratorRepository.findTrips10porcentMoreApplicationsThanAvg();
		return result;
	}

	public Collection<Integer> findNumOfTimesALegalTextIsReferenced() {
		Collection<Integer> result;
		result = this.administratorRepository.findNumOfTimesALegalTextIsReferenced();
		return result;
	}

	public Double[] findMaxMinAvgStddevOfTheNumOfNotesPerTrip() {
		Double[] result;
		result = this.administratorRepository.findMaxMinAvgStddevOfTheNumOfNotesPerTrip();
		Assert.notNull(result);
		return result;
	}

	public Double[] findMaxMinAvgStddevOfTheNumOfAuditRecordsPerTrip() {
		Double[] result;
		result = this.administratorRepository.findMaxMinAvgStddevOfTheNumOfAuditRecordsPerTrip();
		Assert.notNull(result);
		return result;
	}

	public Double findTheRatOfTripsWihoutAnAuditRecord() {
		Double result;
		result = this.administratorRepository.findTheRatOfTripsWihoutAnAuditRecord();
		return result;
	}

	public Double findTheRatOfRangersWhoHaveRegisteredCurricula() {
		Double result;
		result = this.administratorRepository.findTheRatOfRangersWhoHaveRegisteredCurricula();
		return result;
	}

	public Double findTheRatOfRangersWhoseCurrIsEndorsed() {
		Double result;
		result = this.administratorRepository.findTheRatOfRangersWhoseCurrIsEndorsed();
		return result;
	}

	public Double findTheRatioOFSuspiciousManagers() {
		Double result;
		result = this.administratorRepository.findTheRatioOFSuspiciousManagers();
		return result;
	}

	public Double findTheRatioOFSuspiciousRangers() {
		Double result;
		result = this.administratorRepository.findTheRatioOFSuspiciousRangers();
		return result;
	}

}
