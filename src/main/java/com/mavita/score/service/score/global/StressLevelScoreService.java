package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService for calculating stress level score from {@link HealthDTO}.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@code RARELY} &rarr; 0 points
 *   <li>{@code SOMETIMES} &rarr; 2 points
 *   <li>{@code YES} &rarr; 4 points
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setStressLevelScore(int)}.
 *
 * @since 1.0.0
 */
@Service
public class StressLevelScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toStressLevelScore(healthDTO);
    healthScoreSummaryDTO.setStressLevelScore(score);
  }

  private int toStressLevelScore(HealthDTO healthDTO) {
    final var level = healthDTO.stressLevel();
    if (level == null) return 0;

    return switch (level) {
      case "RARELY" -> 0;
      case "SOMETIMES" -> 2;
      case "YES" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + level);
    };
  }
}
