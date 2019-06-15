package intuit.eboard.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;
import org.hibernate.validator.constraints.Range;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Rating {

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  @NonNull
  @Range(max = 10)
  private Short rate;

  @OneToOne
  @NonNull
  @Exclude
  private Citizen rater;

  @OneToOne
  @NonNull
  @Exclude
  private Idea idea;

}
