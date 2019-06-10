package intuit.eboard.ui;

import intuit.eboard.model.Contender;
import intuit.eboard.model.Manifesto;
import intuit.eboard.service.ContenderService;
import intuit.eboard.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ContenderShellComponent {

  private ContenderService contenderService;

  private IdeaService ideaService;

  @Autowired
  public ContenderShellComponent(ContenderService contenderService, IdeaService ideaService) {
    this.contenderService = contenderService;
    this.ideaService = ideaService;
  }

  @ShellMethod("List all contenders")
  public Iterable<Contender> listContenders() {
    return contenderService.getAllContenders();
  }

  @ShellMethod("Post manifesto on Board")
  public void postManifesto(Manifesto manifesto) {
    manifestoService.postManifestoOntoBoard(manifesto);
  }

  @ShellMethod("Post an idea")
  public void postIdea(String contenderName, String idea) {
    ideaService.postIdea(contenderName, idea);
  }

}
