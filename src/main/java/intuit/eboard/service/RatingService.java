package intuit.eboard.service;

import intuit.eboard.model.Citizen;
import intuit.eboard.model.Contender;
import intuit.eboard.model.Idea;
import intuit.eboard.model.Rating;
import intuit.eboard.persistency.RatingRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class RatingService {

  private Logger logger = LoggerFactory.getLogger(RatingService.class);

  @Value("${minimum_rating_to_become_follower_automatically:5}")
  private short minimumRatingToBecomeAFollowerAutomatically;

  @Value("${minimum_rating:5}")
  private short minimumRating;

  @Value("${number_of_ratings_below_threshold:3}")
  private short numberOfRatingsBelowThreshold;

  private RatingRepository ratingRepository;

  private ContenderService contenderService;

  private CitizenService citizenService;

  private IdeaService ideaService;

  public RatingService(RatingRepository ratingRepository, ContenderService contenderService,
      CitizenService citizenService, IdeaService ideaService) {
    this.ratingRepository = ratingRepository;
    this.contenderService = contenderService;
    this.citizenService = citizenService;
    this.ideaService = ideaService;
  }

  @Transactional
  public Rating rateIdea(UUID ideaId, @Range(max = 10) Short rating, String raterName) {
    Optional<Citizen> optionalRater = citizenService.findCitizenByName(raterName);
    if (optionalRater.isEmpty()) {
      logger.warn("Couldn't find a citizen named {}", raterName);
      return null;
    }

    Optional<Idea> optionalIdea = ideaService.findById(ideaId);
    if (optionalIdea.isEmpty()) {
      logger.warn("Couldn't find an idea with ID {}", ideaId);
      return null;
    }

    return this.save(new Rating(rating, optionalRater.get(), optionalIdea.get()));
  }

  public void deleteRating(UUID ratingId) {
    ratingRepository.deleteById(ratingId);
  }

  public Rating save(@Valid Rating rating) {
    Contender contender = rating.getIdea().getContender();
    Short rate = rating.getRate();


    promoteVoterAsAFollowerIfAppropriate(rating, contender, rate);
    removeContenderFromElectionIfAppropriate(contender, rating);

    return ratingRepository.save(rating);
  }

  public Stream<Rating> getAllRatings() {
    return ratingRepository.streamAll();
  }

  private void promoteVoterAsAFollowerIfAppropriate(Rating rating, Contender contender,
      Short rate) {
    if (rate > minimumRatingToBecomeAFollowerAutomatically) {
      Citizen rater = rating.getRater();
      logger.info("{} Added as a follower of {} because he rated {}", rater, contender, rating);
      contender.getFollowers().add(rater);
    }
  }

  private void removeContenderFromElectionIfAppropriate(Contender contender, Rating rating) {
    if (rating.getRate() < minimumRating) {
      logger.debug("Rating below minimumRating!");

      Predicate<Rating> ratingsLowerThanThreshold = rating1 -> rating1.getRate() < minimumRating;

      long currentNumberOfRatingsBelowThreshold = rating
          .getIdea()
          .getRatings()
          .stream()
          .filter(ratingsLowerThanThreshold)
          .count();

      boolean maximumNumberOfRatingsBellowThresholdAchieved =
          currentNumberOfRatingsBelowThreshold + 1
              > numberOfRatingsBelowThreshold;

      logger
          .debug("currentNumberOfRatingsBelowThreshold: {}", currentNumberOfRatingsBelowThreshold);

      if (maximumNumberOfRatingsBellowThresholdAchieved) {
        logger
            .info("{} removed from election because this would be the {} rate below {}", contender,
                currentNumberOfRatingsBelowThreshold + 1,
                minimumRating);
        contenderService.removeFromElection(contender);
      }
    }
  }
}
