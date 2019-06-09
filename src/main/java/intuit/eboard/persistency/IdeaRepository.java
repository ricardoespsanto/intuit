package intuit.eboard.persistency;

import intuit.eboard.model.Idea;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, UUID> {

}
