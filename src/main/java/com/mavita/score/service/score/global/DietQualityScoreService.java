package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates a score based on diet quality.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@code HEALTHY} &rarr; 0 points
 *   <li>{@code AVERAGE} &rarr; 2 points
 *   <li>{@code UNHEALTHY} &rarr; 4 points
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setDietScore(int)}.
 */
@Service
public class DietQualityScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toDietScore(healthDTO);
    healthScoreSummaryDTO.setDietScore(score);
  }

  private int toDietScore(HealthDTO healthDTO) {
    final var dq = healthDTO.dietQuality();
    if (dq == null) return 0;

    return switch (dq) {
      case "HEALTHY" -> 0;
      case "AVERAGE" -> 2;
      case "UNHEALTHY" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + dq);
    };
  }
}
