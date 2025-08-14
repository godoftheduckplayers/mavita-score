package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import com.mavita.score.service.score.pointer.dto.PointerScoreDTO;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the hypertension risk pointer score based on individual
 * hypertension-related domain scores.
 *
 * <p>The score is calculated by summing alcohol consumption, headache/dizziness, personal
 * hypertension history, and family hypertension history scores, capped at a maximum of 18 points.
 *
 * <p>Score interpretation (0–18):
 *
 * <ul>
 *   <li>0–4: Low risk
 *   <li>5–11: Moderate risk
 *   <li>12–18: High risk
 * </ul>
 *
 * The resulting score is set in the PointerResultDTO.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class HypertensionRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 18;

  @Override
  public PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO) {

    int totalScore =
        healthScoreSummaryDTO.getAlcoholConsumptionScore()
            + healthScoreSummaryDTO.getHeadacheDizzinessLevelScore()
            + healthScoreSummaryDTO.getChronicConditionScore()
            + healthScoreSummaryDTO.getParentalConditionsScore();

    totalScore = getTotalScore(totalScore);

    PointerScoreDTO hypertensionRiskPointerScore = new PointerScoreDTO(totalScore, MAX_SCORE);
    pointerResultDTO.setHypertensionRiskPointerScore(hypertensionRiskPointerScore);

    return pointerResultDTO;
  }

  private int getTotalScore(int totalScore) {
    if (totalScore > MAX_SCORE) {
      totalScore = MAX_SCORE;
    }
    return totalScore;
  }
}
