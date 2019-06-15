package intuit.eboard.model;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Contender {

  @Id
  @GeneratedValue
  private UUID id;

  @NonNull
  @OneToOne
  @Cascade(CascadeType.ALL)
  private Citizen citizen;

  @OneToMany(mappedBy = "contender")
  @Cascade(CascadeType.ALL)
  @Size(min = 1, max = 3)
  private Collection<Idea> ideas;

  @ManyToMany
  @Cascade(CascadeType.ALL)
  @Exclude
  private Collection<Citizen> followers;

}
