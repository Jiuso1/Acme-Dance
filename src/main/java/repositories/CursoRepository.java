
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Academia;
import domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	@Query("select c from Curso c where c.academia = ?1")
	Collection<Curso> findByAcademia(Academia a);
	@Query("select c from Curso c where c.id = ?1")
	public Collection<Curso> findById(int id);
}
