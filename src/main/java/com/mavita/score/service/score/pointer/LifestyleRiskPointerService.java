package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import com.mavita.score.service.score.pointer.dto.PointerScoreDTO;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the lifestyle risk pointer score based on individual
 * lifestyle-related domain scores.
 *
 * <p>The score is calculated by summing smoking, alcohol consumption, physical activity, diet, and
 * sleep scores, capped at a maximum of 20 points.
 *
 * <p>Score interpretation (0–20):
 *
 * <ul>
 *   <li>0–6: Healthy lifestyle
 *   <li>7–13: Moderate risk
 *   <li>14–20: High risk
 * </ul>
 *
 * The resulting score is set in the PointerResultDTO.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class LifestyleRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 20;

  @Override
  public PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO) {

    int totalScore =
        healthScoreSummaryDTO.getSmokingScore()
            + healthScoreSummaryDTO.getAlcoholConsumptionScore()
            + healthScoreSummaryDTO.getPhysicalActivityScore()
            + healthScoreSummaryDTO.getDietScore()
            + healthScoreSummaryDTO.getSleepDifficultyScore();

    totalScore = getTotalScore(totalScore);

    PointerScoreDTO lifestyleRiskPointerScore = new PointerScoreDTO(totalScore, MAX_SCORE);
    pointerResultDTO.setLifestyleRiskPointerScore(lifestyleRiskPointerScore);

    return pointerResultDTO;
  }

  private int getTotalScore(int totalScore) {
    if (totalScore > MAX_SCORE) {
      totalScore = MAX_SCORE;
    }
    return totalScore;
  }
}
