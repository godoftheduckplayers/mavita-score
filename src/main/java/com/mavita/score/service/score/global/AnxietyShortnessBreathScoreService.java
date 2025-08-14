package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService to calculate score based on anxiety or shortness of breath without physical effort.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>YES - 4 points
 *   <li>SOMETIMES - 2 points
 *   <li>RARELY - 0 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class AnxietyShortnessBreathScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    int score = getScore(healthDataDTO);
    healthScoreSummaryDTO.setAnxietyShortnessBreathScore(score);
  }

  private int getScore(HealthDataDTO healthDataDTO) {
    return switch (healthDataDTO.anxietyShortnessBreath()) {
      case YES -> 4;
      case SOMETIMES -> 2;
      case RARELY -> 0;
    };
  }
}
