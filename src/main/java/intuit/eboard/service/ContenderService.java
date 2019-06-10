package intuit.eboard.service;

import intuit.eboard.model.Citizen;
import intuit.eboard.model.Contender;
import intuit.eboard.model.Manifesto;
import intuit.eboard.persistency.ContenderRepository;
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

  public void nominateSelf(@NonNull String name) {
    Contender self = new Contender(new Citizen(name), new Manifesto());
    contenderRepository.save(self);
  }

}
