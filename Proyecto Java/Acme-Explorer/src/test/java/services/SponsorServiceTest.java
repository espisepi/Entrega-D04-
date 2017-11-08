
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private SponsorService	sponsorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Sponsor sponsor;
		sponsor = this.sponsorService.create();
		Assert.notNull(sponsor);
	}

	@Test
	public void testFindAll() {

		Collection<Sponsor> sponsors;

		sponsors = this.sponsorService.findAll();

		Assert.notNull(sponsors);
		Assert.notEmpty(sponsors);
	}

}
