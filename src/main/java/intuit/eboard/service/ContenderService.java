package intuit.eboard.service;

import intuit.eboard.model.Citizen;
import intuit.eboard.model.Contender;
import intuit.eboard.model.Idea;
import intuit.eboard.model.Manifesto;
import intuit.eboard.persistency.CitizenRepository;
import intuit.eboard.persistency.ContenderRepository;
import intuit.eboard.persistency.ManifestoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ContenderService {

  private ContenderRepository contenderRepository;

  private ManifestoRepository manifestoRepository;

  private CitizenRepository citizenRepository;

  public ContenderService(ContenderRepository contenderRepository,
      ManifestoRepository manifestoRepository, CitizenRepository citizenRepository) {
    this.contenderRepository = contenderRepository;
    this.manifestoRepository = manifestoRepository;
    this.citizenRepository = citizenRepository;
  }

  public Iterable<Contender> getAllContenders() {
    return contenderRepository.findAll();
  }

  public void nominateSelf(@NonNull String name) {
    Citizen citizen = citizenRepository.save(new Citizen(name));
    Manifesto manifesto = manifestoRepository.save(new Manifesto());
    Contender self = new Contender(citizen, manifesto);
    contenderRepository.save(self);
  }

  public void postManifestoOntoBoard(Manifesto manifesto) {
    manifestoRepository.save(manifesto);
  }

  public void postIdea(Idea idea) {

  }

}
