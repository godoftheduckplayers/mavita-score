package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import com.mavita.score.service.score.pointer.dto.PointerScoreDTO;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the diabetes risk pointer score based on individual
 * diabetes-related domain scores.
 *
 * <p>The score is calculated by summing BMI, diabetes symptoms, poor sleep, personal diabetes
 * history, and family diabetes history scores, capped at a maximum of 22 points.
 *
 * <p>Score interpretation (0–22):
 *
 * <ul>
 *   <li>0–6: Low risk
 *   <li>7–14: Moderate risk
 *   <li>15–22: High risk
 * </ul>
 *
 * The resulting score is set in the PointerResultDTO.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class DiabetesRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 22;

  @Override
  public PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO) {

    int totalScore =
        healthScoreSummaryDTO.getBmiScore()
            + healthScoreSummaryDTO.getDiabetesSymptomLevelScore()
            + healthScoreSummaryDTO.getSleepDifficultyScore()
            + healthScoreSummaryDTO.getChronicConditionScore()
            + healthScoreSummaryDTO.getParentalConditionsScore();

    totalScore = getTotalScore(totalScore);

    PointerScoreDTO diabetesRiskPointerScore = new PointerScoreDTO(totalScore, MAX_SCORE);
    pointerResultDTO.setDiabetesRiskPointerScore(diabetesRiskPointerScore);

    return pointerResultDTO;
  }

  private int getTotalScore(int totalScore) {
    if (totalScore > MAX_SCORE) {
      totalScore = MAX_SCORE;
    }
    return totalScore;
  }
}
