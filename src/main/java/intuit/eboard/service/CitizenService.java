package intuit.eboard.service;

import intuit.eboard.model.Citizen;
import intuit.eboard.persistency.CitizenRepository;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CitizenService {

  private CitizenRepository citizenRepository;

  public CitizenService(CitizenRepository citizenRepository) {
    this.citizenRepository = citizenRepository;
  }

  public Optional<Citizen> findCitizenByName(String citizenName) {
    return citizenRepository.findByName(citizenName);
  }

  public Citizen save(@Valid Citizen citizen) {
    return citizenRepository.save(citizen);
  }

  public Iterable<Citizen> findAll() {
    return citizenRepository.findAll();
  }
}
