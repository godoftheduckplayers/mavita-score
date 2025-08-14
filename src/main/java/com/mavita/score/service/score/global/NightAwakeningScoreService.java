package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on how frequently the user wakes up more than twice
 * per night.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>RARELY - 0 points
 *   <li>SOMETIMES - 2 points
 *   <li>ALMOST_EVERY_DAY - 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class NightAwakeningScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setNightAwakeningFrequencyScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.nightAwakeningFrequency()) {
      case RARELY -> 0;
      case SOMETIMES -> 2;
      case ALMOST_EVERY_DAY -> 4;
    };
  }
}
