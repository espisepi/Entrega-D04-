
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LegalTextRepository;
import domain.LegalText;

@Service
@Transactional
public class LegalTextService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private LegalTextRepository		legalTextRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;


	// Constructors------------------------------------------------------------
	public LegalTextService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public LegalText create() {
		//TODO: ¿Sólo los ADMINISTRATOR puden crear un legalText?
		LegalText result;
		Date moment;

		result = new LegalText();
		moment = new Date(System.currentTimeMillis() - 1000);

		result.setMoment(moment);
		return result;
	}

	public Collection<LegalText> findAll() {
		Collection<LegalText> result;
		Assert.notNull(this.legalTextRepository);
		result = this.legalTextRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public LegalText findOne(final int legalTextId) {
		LegalText result;
		result = this.legalTextRepository.findOne(legalTextId);
		return result;
	}

	public LegalText save(final LegalText legalText) {
		//TODO: Comprobar que solo sea el usuario autenticado como ADMINISTRATOR
		//quien puede modificar.

		//Compruebo legalText no sea nulo
		Assert.notNull(legalText);
		//Compruebo que no está guardado en modo final para poder editarlo
		Assert.isTrue(legalText.isDraftMode() == true);
		LegalText result;

		result = this.legalTextRepository.save(legalText);

		return result;
	}
	public void delete(final LegalText legalText) {
		Assert.isTrue(legalText.getId() != 0);
		Assert.notNull(legalText);
		Assert.isTrue(this.legalTextRepository.exists(legalText.getId()));
		//Compruebo que no esté guardado en modo final y poder borrarlo
		Assert.isTrue(legalText.isDraftMode() == true);

		this.legalTextRepository.delete(legalText);
	}
}
