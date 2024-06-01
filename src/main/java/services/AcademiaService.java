
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Academia;
import repositories.AcademiaRepository;

@Service
@Transactional
public class AcademiaService {

	@Autowired
	private AcademiaRepository academiaRepository;


	public Collection<Academia> findAll() {
		Collection<Academia> result;

		result = this.academiaRepository.findAll();

		return result;
	}

	public void save(final Academia academia) {
		this.academiaRepository.save(academia);
	}

}
