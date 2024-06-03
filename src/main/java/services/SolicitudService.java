
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Alumno;
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
}
