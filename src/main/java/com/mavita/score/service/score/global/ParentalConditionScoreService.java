package com.mavita.score.service.score.global;

import com.mavita.score.exception.InvalidParentalConditionSelectionException;
import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.global.enums.ParentalCondition;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ScoreService to calculate score based on parental chronic conditions.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>Each selected condition (except NONE) = 5 pts
 *   <li>NONE alone = 0 pts
 * </ul>
 *
 * Throws {@link InvalidParentalConditionSelectionException} if NONE is combined with any other
 * option.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class ParentalConditionScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    List<ParentalCondition> conditions = healthDataDTO.parentalConditions();
    int score = getScore(conditions);
    healthScoreSummaryDTO.setParentalConditionsScore(score);
  }

  private int getScore(List<ParentalCondition> conditions) {
    if (conditions.contains(ParentalCondition.NONE) && conditions.size() > 1) {
      throw new InvalidParentalConditionSelectionException(
          "'NONE' must not be selected with other conditions");
    }
    return (conditions.contains(ParentalCondition.NONE)) ? 0 : conditions.size() * 5;
  }
}
