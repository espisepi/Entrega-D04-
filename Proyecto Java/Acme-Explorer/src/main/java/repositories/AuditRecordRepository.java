
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AuditRecord;

@Repository
public interface AuditRecordRepository extends JpaRepository<AuditRecord, Integer> {

	@Query("select a from AuditRecord a where a.draftMode= true and a.auditor.id=?1")
	Collection<AuditRecord> findAuditRecordInDraftMode(int auditorId);
}
