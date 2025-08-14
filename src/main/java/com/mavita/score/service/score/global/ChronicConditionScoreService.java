package com.mavita.score.service.score.global;

import com.mavita.score.exception.InvalidChronicConditionSelectionException;
import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.global.enums.ChronicCondition;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates score based on reported chronic conditions.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>Each condition = 5 pts (excluding NONE)
 *   <li>If ONLY NONE is selected = 0 pts
 * </ul>
 *
 * * Throws {@link InvalidChronicConditionSelectionException} if NONE is combined with any other
 * option.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class ChronicConditionScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    List<ChronicCondition> conditions = healthDataDTO.chronicConditions();
    int score = getScore(conditions);
    healthScoreSummaryDTO.setChronicConditionScore(score);
  }

  private int getScore(List<ChronicCondition> conditions) {
    if (conditions.contains(ChronicCondition.NONE) && conditions.size() > 1) {
      throw new InvalidChronicConditionSelectionException(
          "'NONE' must not be selected with other conditions");
    }
    return (conditions.contains(ChronicCondition.NONE)) ? 0 : conditions.size() * 5;
  }
}
