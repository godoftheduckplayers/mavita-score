package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on how often the user feels healthy.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>YES → 0 points
 *   <li>SOMETIMES → 2 points
 *   <li>RARELY → 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class HealthFeelingScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setHealthFeelingScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.healthFeeling()) {
      case YES -> 0;
      case SOMETIMES -> 2;
      case RARELY -> 4;
    };
  }
}
