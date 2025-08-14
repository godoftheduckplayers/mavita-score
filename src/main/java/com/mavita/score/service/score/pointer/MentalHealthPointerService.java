package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import com.mavita.score.service.score.pointer.dto.PointerScoreDTO;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the mental health pointer score based on individual mental
 * health domain scores.
 *
 * <p>The score is calculated by summing anxiety, stress, and sadness scores from the health summary
 * and capped at a maximum of 12 points.
 *
 * <p>Score interpretation (0–12):
 *
 * <ul>
 *   <li>0–3: Good mental health
 *   <li>4–7: Moderate attention needed
 *   <li>8–12: High emotional risk
 * </ul>
 *
 * The resulting score is set in the PointerResultDTO.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class MentalHealthPointerService implements PointerService {

  private static final int MAX_SCORE = 12;

  @Override
  public PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO) {

    int totalScore =
        healthScoreSummaryDTO.getAnxietyShortnessBreathScore()
            + healthScoreSummaryDTO.getStressLevelScore()
            + healthScoreSummaryDTO.getSadnessLevelScore();

    totalScore = getTotalScore(totalScore);

    PointerScoreDTO mentalHealthPointerScore = new PointerScoreDTO(totalScore, MAX_SCORE);
    pointerResultDTO.setMentalHealthPointerScore(mentalHealthPointerScore);

    return pointerResultDTO;
  }

  private int getTotalScore(int totalScore) {
    if (totalScore > MAX_SCORE) {
      totalScore = MAX_SCORE;
    }
    return totalScore;
  }
}
