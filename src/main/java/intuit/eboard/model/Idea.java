package intuit.eboard.model;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

@Data
@Entity
@RequiredArgsConstructor
public class Idea {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  @Type(type = "short")
  @Range(max = 10)
  private Collection<Short> ratings;

}
