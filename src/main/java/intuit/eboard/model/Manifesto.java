package intuit.eboard.model;

import java.util.Collection;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Manifesto {

  @Size(max = 3)
  private Collection<Idea> ideas;

}
