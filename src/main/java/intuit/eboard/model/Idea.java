package intuit.eboard.model;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Idea {

  @Id
  @GeneratedValue
  private UUID id;

  @OneToMany(mappedBy = "idea")
  @Cascade(CascadeType.ALL)
  private Collection<Rating> ratings;

  @Column(nullable = false)
  @NonNull
  private String theIdea;

  @NonNull
  @ManyToOne
  @Exclude
  private Contender contender;

}
