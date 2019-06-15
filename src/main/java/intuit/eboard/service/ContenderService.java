package intuit.eboard.service;

import intuit.eboard.model.Citizen;
import intuit.eboard.model.Contender;
import intuit.eboard.persistency.ContenderRepository;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ContenderService {

  private ContenderRepository contenderRepository;

  public ContenderService(ContenderRepository contenderRepository) {
    this.contenderRepository = contenderRepository;
  }

  public Iterable<Contender> getAllContenders() {
    return contenderRepository.findAll();
  }

  public Contender newContender(@NonNull String name) {
    return contenderRepository.save(new Contender(new Citizen(name)));
  }

  public Optional<Contender> findContenderByName(String contenderName) {
    return contenderRepository.findContenderByCitizenName(contenderName);
  }

  public void removeFromElection(Contender contender) {
    contenderRepository.delete(contender);
  }
}
