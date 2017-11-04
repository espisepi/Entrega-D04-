
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.LegalText;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class LegalTextServceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private LegalTextService	legalTextService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreatePositive() {
		LegalText legalText;
		legalText = this.legalTextService.create();
		Assert.notNull(legalText);
	}

	@Test
	public void testSavePositive() {
		LegalText legalText;
		legalText = this.legalTextService.create();

		legalText.setTitle("title 1");
		legalText.setBody("body 1");
		legalText.setLawsNumber(1);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:ii");
		String stringMoment = "2017/09/15 22:45";
		Date moment;
		try {
			moment = sdf1.parse(stringMoment);
			legalText.setMoment(moment);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		legalText.setDraftMode(true);

	}
}
