
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiscellaneousRecordRepository extends JpaRepository<MiscellaneousRecordRepository, Integer> {

}
