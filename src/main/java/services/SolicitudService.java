
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Academia;
import domain.Alumno;
import domain.Curso;
import domain.Solicitud;
import repositories.SolicitudRepository;

@Service
@Transactional
public class SolicitudService {

	@Autowired
	private SolicitudRepository solicitudRepository;


	public Collection<Solicitud> findAll() {
		Collection<Solicitud> result;
		result = this.solicitudRepository.findAll();
		return result;
	}

	public Collection<Solicitud> findByAlumno(final Alumno a) {
		Collection<Solicitud> result;
		result = this.solicitudRepository.findByAlumno(a);
		return result;
	}

	public long countByAlumnoAndCurso(final Alumno a, final Curso c) {
		long result;
		result = this.solicitudRepository.countByAlumnoAndCurso(a, c);
		return result;
	}

	public Collection<Solicitud> findByAcademia(final Academia a) {
		Collection<Solicitud> result;
		result = this.solicitudRepository.findByAcademia(a);
		return result;
	}

	public Collection<Solicitud> findById(final int id) {
		Collection<Solicitud> result;
		result = this.solicitudRepository.findById(id);
		return result;
	}

	public void save(final Solicitud s) {
		this.solicitudRepository.save(s);
	}
}
