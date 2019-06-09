package intuit.eboard.service;

import intuit.eboard.model.Idea;
import org.springframework.stereotype.Service;

@Service
public class IdeaService {

  public void rateIdea(Idea idea,  Short rating) {
    idea.getRatings().add(rating);
    if (rating > 5) {

    }
  }

  public void deleteRating(Idea idea) {

  }
}
