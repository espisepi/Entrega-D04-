
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.ApplicationFor;

public interface ApplicationForRepository extends JpaRepository<ApplicationFor, Integer> {

	@Query("select a from Manager m join m.applicationsFor a where m.id = ?1")
	Collection<ApplicationFor> findAllByManagerId(int managerId);

}
