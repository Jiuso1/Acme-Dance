
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Academia;
import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	@Query("select t from Tutorial t where t.id = ?1")
	public Collection<Tutorial> findById(int id);

	@Query("select t from Tutorial t where t.academia = ?1")
	Collection<Tutorial> findByAcademia(Academia a);
}
