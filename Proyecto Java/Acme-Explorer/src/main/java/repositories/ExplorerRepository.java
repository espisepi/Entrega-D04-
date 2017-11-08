
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Explorer;
import domain.Manager;

public interface ExplorerRepository extends JpaRepository<Explorer, Integer> {

	@Query("select m from Manager m where m.userAccount.id=?1")
	Manager findByUserAccountId(int userAccountId);
}
