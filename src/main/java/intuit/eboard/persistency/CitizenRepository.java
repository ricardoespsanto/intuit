package intuit.eboard.persistency;

import intuit.eboard.model.Citizen;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends CrudRepository<Citizen, UUID> {

}
