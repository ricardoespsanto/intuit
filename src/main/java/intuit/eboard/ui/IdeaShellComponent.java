package intuit.eboard.ui;

import intuit.eboard.model.Idea;
import intuit.eboard.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("Idea Menu")
public class IdeaShellComponent {

  private IdeaService ideaService;

  @Autowired
  public IdeaShellComponent(IdeaService ideaService) {
    this.ideaService = ideaService;
  }

  @ShellMethod("Post an idea")
  public Idea postIdea(@NonNull String contenderName, @NonNull String idea) {
    return ideaService.postIdea(contenderName, idea);
  }

}
