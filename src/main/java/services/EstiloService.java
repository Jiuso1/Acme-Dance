
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Estilo;
import repositories.EstiloRepository;

@Service
@Transactional
public class EstiloService {

	@Autowired
	private EstiloRepository estiloRepository;


	public Collection<Estilo> findAll() {
		Collection<Estilo> result;

		result = this.estiloRepository.findAll();

		return result;
	}

	public Collection<Estilo> findByName(final String name) {
		Collection<Estilo> result;

		result = this.estiloRepository.findByName(name);

		return result;
	}

	public Collection<Estilo> findById(final int id) {
		Collection<Estilo> result;

		result = this.estiloRepository.findById(id);

		return result;
	}

	public void delete(final int id) {
		this.estiloRepository.delete(id);
	}

	public void save(final Estilo estilo) {
		this.estiloRepository.save(estilo);
	}
}
