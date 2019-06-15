package intuit.eboard.ui;

import intuit.eboard.model.Citizen;
import intuit.eboard.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("Citizen Menu")
public class CitizenShellComponent {

  private CitizenService citizenService;

  @Autowired
  public CitizenShellComponent(CitizenService citizenService) {
    this.citizenService = citizenService;
  }

  @ShellMethod("Register a citizen")
  public Citizen registerCitizen(@NonNull String citizenName) {
    return citizenService.save(new Citizen(citizenName));
  }

  @ShellMethod("List all citizens")
  public Iterable<Citizen> listAllCitizens() {
    return citizenService.findAll();
  }

}
