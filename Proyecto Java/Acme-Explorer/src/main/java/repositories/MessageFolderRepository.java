
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.MessageFolder;

@Repository
public interface MessageFolderRepository extends JpaRepository<MessageFolder, Integer> {

}