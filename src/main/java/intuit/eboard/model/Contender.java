package intuit.eboard.model;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Contender {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  @NonNull
  @OneToOne
  private Citizen self;

  @OneToOne
  @NonNull
  private Manifesto manifesto;

  @ManyToMany
  @Column(nullable = false)
  private Collection<Citizen> followers;

}
