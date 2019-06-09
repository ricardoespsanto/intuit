package intuit.eboard.ui;

import intuit.eboard.model.Contender;
import intuit.eboard.model.Idea;
import intuit.eboard.model.Manifesto;
import intuit.eboard.service.ContenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ContenderShellComponent {

  private ContenderService contenderService;

  @Autowired
  public ContenderShellComponent(ContenderService contenderService) {
    this.contenderService = contenderService;
  }

  @ShellMethod(value = "List all contenders")
  public Iterable<Contender> listContenders() {
    return contenderService.getAllContenders();
  }

  @ShellMethod(value = "Post manifesto on Board")
  public void postManifesto(Manifesto manifesto) {
    contenderService.postManifestoOntoBoard(manifesto);
  }

  @ShellMethod
  public void postIdea(Idea idea) {
    contenderService.postIdea(idea);
  }

}
