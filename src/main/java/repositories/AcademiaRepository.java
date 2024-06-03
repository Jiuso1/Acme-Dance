
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Integer> {

	@Query("select a from Academia a where a.username = ?1")
	public Collection<Academia> findByUsername(String username);
}
