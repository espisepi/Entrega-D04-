
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.SocialIdentity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SocialIdentityServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private SocialIdentityService	socialIdentityService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		SocialIdentity socialIdentity;
		socialIdentity = this.socialIdentityService.create();
		Assert.notNull(socialIdentity);
	}

	@Test
	public void testFindOne() {
		SocialIdentity socialIdentty;
		socialIdentty = this.socialIdentityService.findOne(6041);

		Assert.notNull(socialIdentty);
	}

	@Test
	public void testSave() {
		SocialIdentity socialIdentity;
		socialIdentity = this.socialIdentityService.create();

		socialIdentity.setLink("http://www.test.com");
		socialIdentity.setPhoto("http://www.test.com");
		socialIdentity.setName("test");
		socialIdentity.setNick("test1");
		this.socialIdentityService.save(socialIdentity);
	}

	@Test
	public void testDelete() {
		SocialIdentity socialIdentity;
		socialIdentity = this.socialIdentityService.findOne(6065);
		this.socialIdentityService.delete(socialIdentity);
	}
}
