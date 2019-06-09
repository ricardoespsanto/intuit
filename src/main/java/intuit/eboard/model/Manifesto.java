package intuit.eboard.model;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Manifesto {

  @Id
  @GeneratedValue
  private UUID id;

  @OneToMany(fetch = FetchType.EAGER)
  @Max(3)
  private Collection<Idea> ideas;

}
