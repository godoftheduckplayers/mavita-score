package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates points based on preventive exam frequency.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>YES - 0 pts
 *   <li>SOMETIMES - 2 pts
 *   <li>NO - 4 pts
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class PreventiveExamScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setPreventiveExamFrequencyScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.preventiveExamFrequency()) {
      case YES -> 0;
      case SOMETIMES -> 2;
      case NO -> 4;
    };
  }
}
