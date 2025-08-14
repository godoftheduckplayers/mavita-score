package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService for calculating sadness and lack of motivation score.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>RARELY - 0 points
 *   <li>SOMETIMES - 2 points
 *   <li>YES - 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class SadnessLevelScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setSadnessLevelScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.sadnessLevel()) {
      case RARELY -> 0;
      case SOMETIMES -> 2;
      case YES -> 4;
    };
  }
}
