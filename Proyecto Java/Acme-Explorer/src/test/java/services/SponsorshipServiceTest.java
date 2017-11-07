
package services;

import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Sponsorship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorshipServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private SponsorshipService	sponsorshipService;

	@Autowired
	private TripService			tripService;

	@Autowired
	private SponsorService		sponsorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		Sponsorship res;
		res = this.sponsorshipService.create();
		Assert.notNull(res);
	}

	@Test
	public void testFindAll() {
		Collection<Sponsorship> res = this.sponsorshipService.findAll();
		Assert.notEmpty(res);
	}

	//	@Test
	//	public void testSave() {
	//		Sponsorship sponsorship;
	//		CreditCard creditcard;
	//		Sponsor sponsor;
	//
	//		sponsorship = this.sponsorshipService.create();
	//		creditcard = new CreditCard();
	//		Trip trip = this.tripService.findAll().iterator().next();
	//		//sponsor = this.sponsorService.findAll().iterator().next(); // falla el findall de sponsor
	//		
	//		sponsor = new Sponsor();
	//		sponsor.setAddress("eni");
	//		sponsor.setEmail("nier@gmail.com")
	//		sponsor.setName(name)
	//		creditcard.setBrandName("brandName");
	//		creditcard.setHolderName("holderName");
	//		creditcard.setNumber("4388576018410707");
	//		creditcard.setExpirationMonth(2);
	//		creditcard.setExpirationYear(2019);
	//		creditcard.setCvv(655);
	//
	//		sponsorship.setLink("http://www.link-banner.com");
	//		sponsorship.setBannerURL("http://www.banner.com");
	//		sponsorship.setCreditCard(creditcard);
	//		sponsorship.setTrip(trip);
	//		//sponsorship.setSponsor(sponsor);
	//
	//		sponsorship = this.sponsorshipService.save(sponsorship);
	//	}

	@Test
	public void testDelete() {
		Collection<Sponsorship> sponsorships;
		Iterator<Sponsorship> sponsorship; // para poder recorrer la colección

		sponsorships = this.sponsorshipService.findAll();
		System.out.println(this.sponsorshipService.findAll());
		sponsorship = sponsorships.iterator();

		this.sponsorshipService.delete(sponsorship.next());
		System.out.println(this.sponsorshipService.findAll());

	}
}
