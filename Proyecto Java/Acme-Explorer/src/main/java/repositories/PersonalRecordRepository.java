
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;
import domain.PersonalRecord;

@Repository
public interface PersonalRecordRepository extends JpaRepository<PersonalRecord, Integer> {

	@Query("select c from Curricula c where c.personalRecord.id=?1")
	Curricula CurriculaWithThisPersonalRecord(int personalRecordId);
}
