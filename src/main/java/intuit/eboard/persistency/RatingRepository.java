package intuit.eboard.persistency;

import intuit.eboard.model.Rating;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, UUID> {

  @Query("select r from Rating r")
  Stream<Rating> streamAll();

}
