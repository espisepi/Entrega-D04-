
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;
import domain.LegalText;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class LegalTextServceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private LegalTextService		legalTextService;

	@Autowired
	private AdministratorService	administradorService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreate() {

		this.authenticate("administrator1");

		LegalText legalText;
		Administrator administrator;

		administrator = this.administradorService.findByPrincipal();
		legalText = this.legalTextService.create(administrator);
		this.administradorService.checkPrincipal();
		Assert.notNull(legalText);
	}

	@Test
	public void testFindAll() {

		Collection<LegalText> result;

		result = this.legalTextService.findAll();

		Assert.notNull(result);
		Assert.notEmpty(result);

	}

	@Test
	public void testFindOne() {

		LegalText result;

		result = this.legalTextService.findOne(super.getEntityId("legalText5"));

		Assert.notNull(result);

	}

	@Test
	public void testSave() {

		LegalText resultSaved;
		LegalText result;
		Administrator administrator;

		this.authenticate("administrator1");

		administrator = this.administradorService.findByPrincipal();
		result = this.legalTextService.create(administrator);
		result.setTitle("title test");
		result.setBody("body test");
		result.setLawsNumber(4);
		this.administradorService.checkPrincipal();
		Assert.notNull(result);

		resultSaved = this.legalTextService.save(result);

		Assert.notNull(resultSaved);

	}

	@Test
	public void testSavedUpdate() {

		LegalText result;
		LegalText resultSaved;

		result = this.legalTextService.findOne(super.getEntityId("legalText1"));
		result.setTitle("MIRAR FECHA");

		resultSaved = this.legalTextService.save(result);

		Assert.notNull(resultSaved);

	}

	@Test
	@Rollback(false)
	public void testDeletePositive() {

		LegalText resultSaved;
		LegalText result;
		Administrator administrator;

		this.authenticate("administrator1");

		administrator = this.administradorService.findByPrincipal();
		result = this.legalTextService.create(administrator);
		result.setTitle("title test");
		result.setBody("body test");
		result.setLawsNumber(4);
		this.administradorService.checkPrincipal();
		Assert.notNull(result);

		resultSaved = this.legalTextService.save(result);

		Assert.notNull(resultSaved);

		this.legalTextService.delete(resultSaved);

		Assert.isTrue(!this.legalTextService.findAll().contains(resultSaved));

	}

	@Test
	public void testFindOneToEdit() {

		this.authenticate("administrator1");

		LegalText result;

		result = this.legalTextService.findOneToEdit(super.getEntityId("legalText1"));

		result.setLawsNumber(5);

		Assert.notNull(result);

	}

	//	@Test
	//	public void testFindOneToEditNegative() {
	//
	//		this.authenticate("administrator2");
	//		LegalText result;
	//
	//		result = this.legalTextService.findOneToEdit(super.getEntityId("legalText2"));
	//
	//		result.setLawsNumber(5);
	//
	//		Assert.notNull(result);
	//
	//	}
}
