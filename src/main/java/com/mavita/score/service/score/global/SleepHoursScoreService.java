package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates points based on the user's average sleep window (categorical), as
 * reported in {@link HealthDTO}.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@code BETWEEN_SEVEN_AND_EIGHT} &rarr; 0 pts
 *   <li>{@code BETWEEN_SIX_AND_SEVEN} &rarr; 2 pts
 *   <li>{@code MORE_THAN_EIGHT} &rarr; 2 pts
 *   <li>{@code LESS_THAN_SIX} &rarr; 4 pts
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setSleepHoursScore(int)}.
 *
 * @since 1.0.0
 */
@Service
public class SleepHoursScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toSleepWindowScore(healthDTO.averageSleepWindow());
    healthScoreSummaryDTO.setSleepHoursScore(score);
  }

  private int toSleepWindowScore(String window) {
    if (window == null) return 0;

    return switch (window) {
      case "BETWEEN_SEVEN_AND_EIGHT" -> 0;
      case "BETWEEN_SIX_AND_SEVEN", "MORE_THAN_EIGHT" -> 2;
      case "LESS_THAN_SIX" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + window);
    };
  }
}
