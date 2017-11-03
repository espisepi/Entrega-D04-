
package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.TagRepository;

@Service
@Transactional
public class TagService {

	// Managed repository -----------------------------------------------------
	private TagRepository	tagRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public TagService() {
		super();
	}

}
