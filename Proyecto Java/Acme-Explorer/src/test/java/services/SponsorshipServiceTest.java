
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

}
