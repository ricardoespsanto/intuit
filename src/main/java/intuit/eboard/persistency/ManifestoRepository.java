package intuit.eboard.persistency;

import intuit.eboard.model.Manifesto;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManifestoRepository extends CrudRepository<Manifesto, UUID> {

}
