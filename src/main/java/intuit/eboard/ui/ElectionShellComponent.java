package intuit.eboard.ui;

import intuit.eboard.model.Contender;
import intuit.eboard.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("Election Menu")
public class ElectionShellComponent {

  private ElectionService electionService;

  @Autowired
  public ElectionShellComponent(ElectionService electionService) {
    this.electionService = electionService;
  }

  @ShellMethod("Run the election")
  public Contender runElection() {
    return electionService.electWinner();
  }
}
