package intuit.eboard.ui;

import intuit.eboard.model.Contender;
import intuit.eboard.service.ContenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("Contender Menu")
public class ContenderShellComponent {

  private ContenderService contenderService;

  @Autowired
  public ContenderShellComponent(ContenderService contenderService) {
    this.contenderService = contenderService;
  }

  @ShellMethod("List all contenders")
  public Iterable<Contender> listAllContenders() {
    return contenderService.getAllContenders();
  }

  @ShellMethod("Nominate contender")
  public Contender nominateContender(@NonNull String contender) {
    return contenderService.newContender(contender);
  }

}
