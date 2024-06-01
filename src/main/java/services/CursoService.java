
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Curso;
import repositories.CursoRepository;

@Service
@Transactional
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;


	public Collection<Curso> findAll() {
		Collection<Curso> result;

		result = this.cursoRepository.findAll();

		return result;
	}

	public void save(final Curso curso) {
		this.cursoRepository.save(curso);
	}

}
