package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import com.mavita.score.service.score.pointer.dto.PointerScoreDTO;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the obesity risk pointer score based on individual
 * obesity-related domain scores.
 *
 * <p>The score is calculated by summing BMI, physical activity, diet, poor sleep, personal obesity
 * history, and family obesity history scores, capped at a maximum of 26 points.
 *
 * <p>Score interpretation (0–26):
 *
 * <ul>
 *   <li>0–8: Low risk
 *   <li>9–17: Moderate risk
 *   <li>18–26: High risk
 * </ul>
 *
 * The resulting score is set in the PointerResultDTO.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class ObesityRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 26;

  @Override
  public PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO) {

    int totalScore =
        healthScoreSummaryDTO.getBmiScore()
            + healthScoreSummaryDTO.getPhysicalActivityScore()
            + healthScoreSummaryDTO.getDietScore()
            + healthScoreSummaryDTO.getSleepDifficultyScore()
            + healthScoreSummaryDTO.getChronicConditionScore()
            + healthScoreSummaryDTO.getParentalConditionsScore();

    totalScore = getTotalScore(totalScore);

    PointerScoreDTO obesityRiskPointerScore = new PointerScoreDTO(totalScore, MAX_SCORE);
    pointerResultDTO.setObesityRiskPointerScore(obesityRiskPointerScore);

    return pointerResultDTO;
  }

  private int getTotalScore(int totalScore) {
    if (totalScore > MAX_SCORE) {
      totalScore = MAX_SCORE;
    }
    return totalScore;
  }
}
