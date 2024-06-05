
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Academia;
import domain.Tutorial;
import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository tutorialRepository;


	public Collection<Tutorial> findAll() {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findAll();

		return result;
	}

	public void delete(final int id) {
		this.tutorialRepository.delete(id);
	}

	public void save(final Tutorial tutorial) {
		this.tutorialRepository.save(tutorial);
	}

	public Collection<Tutorial> findById(final int id) {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findById(id);

		return result;
	}

	public Collection<Tutorial> findByAcademia(final Academia a) {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findByAcademia(a);

		return result;
	}
}
