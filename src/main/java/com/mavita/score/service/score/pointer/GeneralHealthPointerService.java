package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import com.mavita.score.service.score.pointer.dto.PointerScoreDTO;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the general health pointer score based on individual health
 * domain scores.
 *
 * <p>This service aggregates relevant health scores such as age, BMI, health perception, and
 * chronic condition history to produce an overall general health indicator score.
 *
 * <p>The calculated score is then set into a PointerResultDTO for further use.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class GeneralHealthPointerService implements PointerService {

  private static final int MAX_SCORE = 62;

  @Override
  public PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO) {

    int totalScore =
        healthScoreSummaryDTO.getAgeScore()
            + healthScoreSummaryDTO.getBmiScore()
            + healthScoreSummaryDTO.getHealthFeelingScore()
            + healthScoreSummaryDTO.getChronicConditionScore()
            + healthScoreSummaryDTO.getParentalConditionsScore();

    totalScore = getTotalScore(totalScore);

    PointerScoreDTO generalHealthPointerScore = new PointerScoreDTO(totalScore, MAX_SCORE);
    pointerResultDTO.setGeneralHealthPointerScore(generalHealthPointerScore);

    return pointerResultDTO;
  }

  private int getTotalScore(int totalScore) {
    if (totalScore > MAX_SCORE) {
      totalScore = MAX_SCORE;
    }
    return totalScore;
  }
}
