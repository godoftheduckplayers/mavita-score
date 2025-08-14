package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates a score based on alcohol consumption habits.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>NONE: 0 points
 *   <li>WEEKENDS: 2 points
 *   <li>DAILY: 4 points
 * </ul>
 *
 * This score reflects the potential health impact of alcohol use frequency.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class AlcoholConsumptionScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setAlcoholConsumptionScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.alcoholConsumption()) {
      case NONE -> 0;
      case WEEKENDS -> 2;
      case DAILY -> 4;
    };
  }
}
