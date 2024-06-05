
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Integer> {

	@Query("select e from Estilo e where e.nombre = ?1")
	public Collection<Estilo> findByName(String name);

	@Query("select e from Estilo e where e.id = ?1")
	public Collection<Estilo> findById(int id);
}
