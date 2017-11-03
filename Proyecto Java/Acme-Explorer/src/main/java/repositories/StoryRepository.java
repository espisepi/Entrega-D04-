
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Story;

public interface StoryRepository extends JpaRepository<Story, Integer> {

}
