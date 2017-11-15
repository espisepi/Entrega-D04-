
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.AuditRecord;

@Repository
public interface AuditRecordRepository extends JpaRepository<AuditRecord, Integer> {
}
