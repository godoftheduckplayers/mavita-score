package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService for calculating points based on frequent headaches or dizziness.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>NO - 0 pts
 *   <li>SOMETIMES - 2 pts
 *   <li>YES - 4 pts
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class HeadacheDizzinessScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setHeadacheDizzinessLevelScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.headacheDizzinessLevel()) {
      case NO -> 0;
      case SOMETIMES -> 2;
      case YES -> 4;
    };
  }
}
