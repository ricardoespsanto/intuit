package intuit.eboard.model;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@Entity
@RequiredArgsConstructor
public class Idea {

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  @Type(type = "short")
  private Collection<Short> ratings;

  @Column(nullable = false)
  @NonNull
  private String theIdea;

}
