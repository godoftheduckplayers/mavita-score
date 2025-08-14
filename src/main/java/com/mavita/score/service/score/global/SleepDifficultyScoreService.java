package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on difficulty falling or staying asleep.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>RARELY - 0 points
 *   <li>SOMETIMES - 2 points
 *   <li>FREQUENTLY - 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class SleepDifficultyScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setSleepDifficultyScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.sleepDifficulty()) {
      case RARELY -> 0;
      case SOMETIMES -> 2;
      case FREQUENTLY -> 4;
    };
  }
}
