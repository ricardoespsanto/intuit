package intuit.eboard.ui;

import intuit.eboard.model.Rating;
import intuit.eboard.service.RatingService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellCommandGroup("Rating Menu")
@ShellComponent
public class RatingShellComponent {

  private RatingService ratingService;

  @Autowired
  public RatingShellComponent(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @ShellMethod("Rate an idea")
  public Rating rateIdea(@NonNull UUID ideaId, @NonNull Short rating, @NonNull String raterName) {
    return ratingService.rateIdea(ideaId, rating, raterName);
  }

  @ShellMethod("Delete a rating")
  public void deleteRating(@NonNull UUID ratingId) {
    ratingService.deleteRating(ratingId);
  }

}
