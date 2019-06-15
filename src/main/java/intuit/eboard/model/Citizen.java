package intuit.eboard.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Citizen {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  @NonNull
  @Length(min = 2)
  private String name;

}
