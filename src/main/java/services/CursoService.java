
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Academia;
import domain.Curso;
import domain.Estilo;
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

	public Collection<Curso> findByAcademia(final Academia a) {
		Collection<Curso> result;

		result = this.cursoRepository.findByAcademia(a);

		return result;
	}

	public Collection<Curso> findById(final int id) {
		Collection<Curso> result;

		result = this.cursoRepository.findById(id);

		return result;
	}

	public void save(final Curso curso) {
		this.cursoRepository.save(curso);
	}

	public void delete(final int id) {
		this.cursoRepository.delete(id);
	}

	public int countCoursesByStyle(final Estilo estilo) {
		int result;

		result = this.cursoRepository.countCoursesByStyle(estilo);

		return result;
	}
}
