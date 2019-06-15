package intuit.eboard.service;

import intuit.eboard.model.Contender;
import intuit.eboard.model.Idea;
import intuit.eboard.persistency.IdeaRepository;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@EnableTransactionManagement
@Validated
public class IdeaService {

  private static final Logger logger = LoggerFactory.getLogger(IdeaService.class);

  @Value("${maximum_number_of_ideas:3}")
  private int maximumNumberOfIdeasPerContender;

  private IdeaRepository ideaRepository;

  private ContenderService contenderService;

  public IdeaService(IdeaRepository ideaRepository, ContenderService contenderService) {
    this.ideaRepository = ideaRepository;
    this.contenderService = contenderService;
  }

  @Transactional
  public Idea postIdea(String contenderName, String idea) {
    Optional<Contender> optionalContender = contenderService.findContenderByName(contenderName);
    if (optionalContender.isEmpty()) {
      logger.warn("Couldn't find a contender by the name {}", contenderName);
      return null;
    }

    Contender contender = optionalContender.get();
    if (contender.getIdeas().size() < maximumNumberOfIdeasPerContender) {
      final Idea savedIdea = ideaRepository.save(new Idea(idea, contender));
      notifyFollowers(savedIdea);
      return savedIdea;
    } else {
      logger.info("Contender {} is only allowed {} ideas", contender,
          maximumNumberOfIdeasPerContender);
      return null;
    }
  }

  private void notifyFollowers(Idea savedIdea) {
    savedIdea.getContender().getFollowers()
        .forEach(citizen -> logger.info("Hi {}, {} just posted a new Idea: {} !", citizen.getName(),
            savedIdea.getContender().getCitizen().getName(), savedIdea.getTheIdea()));
  }

  public Optional<Idea> findById(UUID ideaId) {
    return ideaRepository.findById(ideaId);
  }
}
