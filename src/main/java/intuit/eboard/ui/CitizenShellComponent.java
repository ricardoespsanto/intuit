package intuit.eboard.ui;

import intuit.eboard.model.Idea;
import intuit.eboard.service.ContenderService;
import intuit.eboard.service.IdeaService;
import org.springframework.lang.NonNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CitizenShellComponent {

  private ContenderService contenderService;

  private IdeaService ideaService;

  public CitizenShellComponent(ContenderService contenderService,
      IdeaService ideaService) {
    this.contenderService = contenderService;
    this.ideaService = ideaService;
  }

  @ShellMethod
  public void nominateSelf(@NonNull String ownName) {
    contenderService.nominateSelf(ownName);
  }

  @ShellMethod
  public void rateIdea(@NonNull Idea idea, @NonNull Short rating) {
    ideaService.rateIdea(idea, rating);
  }

  @ShellMethod
  public void deleteOwnRating(@NonNull Idea idea) {
    ideaService.deleteRating(idea);
  }

}
