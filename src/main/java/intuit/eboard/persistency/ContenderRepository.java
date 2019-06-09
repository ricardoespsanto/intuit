package intuit.eboard.persistency;

import intuit.eboard.model.Contender;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenderRepository extends CrudRepository<Contender, UUID> {

}
