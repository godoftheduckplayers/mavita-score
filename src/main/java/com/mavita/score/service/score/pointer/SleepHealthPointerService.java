package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import com.mavita.score.service.score.pointer.dto.PointerScoreDTO;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the sleep health pointer score based on individual
 * sleep-related domain scores.
 *
 * <p>The score is calculated by summing scores for sleep hours, difficulty sleeping, night
 * awakenings, and mood upon waking, capped at a maximum of 16 points.
 *
 * <p>Score interpretation (0–16):
 *
 * <ul>
 *   <li>0–5: Good sleep
 *   <li>6–10: Regular sleep
 *   <li>11–16: Poor sleep
 * </ul>
 *
 * The resulting score is set in the PointerResultDTO.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class SleepHealthPointerService implements PointerService {

  private static final int MAX_SCORE = 16;

  @Override
  public PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO) {

    int totalScore =
        healthScoreSummaryDTO.getSleepHoursScore()
            + healthScoreSummaryDTO.getSleepDifficultyScore()
            + healthScoreSummaryDTO.getNightAwakeningFrequencyScore()
            + healthScoreSummaryDTO.getWakeUpMoodScore();

    totalScore = getTotalScore(totalScore);

    PointerScoreDTO sleepHealthPointerScore = new PointerScoreDTO(totalScore, MAX_SCORE);
    pointerResultDTO.setSleepHealthPointerScore(sleepHealthPointerScore);

    return pointerResultDTO;
  }

  private int getTotalScore(int totalScore) {
    if (totalScore > MAX_SCORE) {
      totalScore = MAX_SCORE;
    }
    return totalScore;
  }
}
