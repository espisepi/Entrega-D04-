
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ConfigurationSystem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ConfigurationSystemServiceTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testFindOne() {
		final ConfigurationSystem res;
		res = this.configurationSystemService.findOne();
		Assert.notNull(res.getBanner());
		Assert.notNull(res.getSpamWords().contains("viagra"));
	}

}
