
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
}
