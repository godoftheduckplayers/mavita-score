package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * Service that calculates score based on average sleep hours per night.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>7.0 to 8.0 hours (inclusive): 0 points
 *   <li>6.0 to 6.59 hours or more than 8.0: 2 points
 *   <li>Less than 6.0 hours: 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class SleepHoursScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Double hours = healthDataDTO.averageSleepHours();
    int score = getScore(hours);
    healthScoreSummaryDTO.setSleepHoursScore(score);
  }

  private int getScore(Double hours) {
    if (hours >= 7.0 && hours <= 8.0) {
      return 0;
    } else if ((hours >= 6.0 && hours < 7.0) || hours > 8.0) {
      return 2;
    } else {
      return 4;
    }
  }
}
