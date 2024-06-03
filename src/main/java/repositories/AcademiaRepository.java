
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Integer> {

}
