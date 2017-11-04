
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PersonalRecord;

@Repository
public interface PersonalRecordRepository extends JpaRepository<PersonalRecord, Integer> {

	@Query("select count(c) from Curricula c where c.personalRecord.id=?1")
	Integer exitsCurriculaWithThisPersonalRecord(int personalRecordId);
}
