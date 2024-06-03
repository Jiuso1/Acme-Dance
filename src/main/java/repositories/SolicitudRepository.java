
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Academia;
import domain.Alumno;
import domain.Curso;
import domain.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

	@Query("select s from Solicitud s where s.alumno = ?1")
	Collection<Solicitud> findByAlumno(Alumno a);

	@Query("select count(s) from Solicitud s where s.alumno = ?1 AND s.curso = ?2")
	long countByAlumnoAndCurso(Alumno a, Curso c);

	@Query("select s from Solicitud s where s.curso in (select c from Curso c where c.academia = ?1)")
	Collection<Solicitud> findByAcademia(Academia a);

	@Query("select s from Solicitud s where s.id = ?1")
	Collection<Solicitud> findById(int id);
}
