package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates score based on diet quality.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>HEALTHY - 0 points
 *   <li>AVERAGE - 2 points
 *   <li>UNHEALTHY - 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class DietQualityScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setDietScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.dietQuality()) {
      case HEALTHY -> 0;
      case AVERAGE -> 2;
      case UNHEALTHY -> 4;
    };
  }
}
