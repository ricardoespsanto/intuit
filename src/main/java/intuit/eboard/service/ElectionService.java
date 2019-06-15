package intuit.eboard.service;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;

import intuit.eboard.model.Contender;
import intuit.eboard.model.Rating;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionService {

  private RatingService ratingService;

  @Autowired
  public ElectionService(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @Transactional
  public Contender electWinner() {
    return ratingService
        .getAllRatings()
        .collect(
            groupingBy(rating -> rating.getIdea().getContender(), averagingInt(Rating::getRate)))
        .entrySet()
        .stream()
        .max(
            comparingDouble(Map.Entry::getValue))
        .get()
        .getKey();
  }

}
