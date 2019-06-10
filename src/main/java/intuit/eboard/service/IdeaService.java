package intuit.eboard.service;

import intuit.eboard.model.Contender;
import intuit.eboard.model.Idea;
import intuit.eboard.model.Manifesto;
import intuit.eboard.persistency.IdeaRepository;
import org.springframework.stereotype.Service;

@Service
public class IdeaService {

  private static final int MAXIMUM_NUMBER_OF_IDEAS_PER_CONTENDER = 4;

  private IdeaRepository ideaRepository;

  public IdeaService(IdeaRepository ideaRepository) {
    this.ideaRepository = ideaRepository;
  }

  public void postIdea(String contenderName, String idea) {
    ideaRepository.save(new Idea(idea));
    contenderRepository.findContenderByCitizenName(contenderName).ifPresent(contender -> addIdeaToManifesto(contender, idea));
  }

  public void rateIdea(Idea idea,  Short rating) {
    idea.getRatings().add(rating);
    if (rating > 5) {

    }
  }

  public void deleteRating(Idea idea) {

  }

  public void addIdeaToManifesto(Contender contender, String idea) {
    Manifesto manifesto = contender.getManifesto();
    if (manifesto.getIdeas().size() < MAXIMUM_NUMBER_OF_IDEAS_PER_CONTENDER) {
      Idea savedIdea = ideaRepository.save(new Idea(idea));
      manifesto.getIdeas().add(savedIdea);
    } else {
      throw new RuntimeException("Contender has reached maximum number of ideas allowed");
    }
  }
}
