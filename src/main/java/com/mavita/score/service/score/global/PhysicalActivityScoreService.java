package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on the user's physical activity level.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>ALWAYS - 0 points
 *   <li>OFTEN - 2 points
 *   <li>RARELY - 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class PhysicalActivityScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setPhysicalActivityScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.physicalActivityLevel()) {
      case ALWAYS -> 0;
      case OFTEN -> 2;
      case RARELY -> 4;
    };
  }
}
