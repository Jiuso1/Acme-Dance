
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

	@Query("select a from Alumno a where a.username = ?1")
	public Collection<Alumno> findByUsername(String username);
}
