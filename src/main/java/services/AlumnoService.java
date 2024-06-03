
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Alumno;
import repositories.AlumnoRepository;

@Service
@Transactional
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;


	public Collection<Alumno> findAll() {
		Collection<Alumno> result;

		result = this.alumnoRepository.findAll();

		return result;
	}

	public Collection<Alumno> findByUsername(final String username) {
		Collection<Alumno> result;

		result = this.alumnoRepository.findByUsername(username);

		return result;
	}

	public void save(final Alumno alumno) {
		this.alumnoRepository.save(alumno);
	}

}
