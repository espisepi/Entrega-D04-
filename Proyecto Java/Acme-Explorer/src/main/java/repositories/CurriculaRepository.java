
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;
import domain.Ranger;

@Repository
public interface CurriculaRepository extends JpaRepository<Curricula, Integer> {

	@Query("select c from Curricula c where c.ranger.id=?1")
	Curricula findCurriculaFromRanger(int rangerId);

	@Query("select c.ranger from Curricula c where c.id=?1")
	Ranger rangerWithThisCurricula(int curriculaID);
}
