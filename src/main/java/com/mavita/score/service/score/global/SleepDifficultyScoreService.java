package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on difficulty falling or staying asleep, as reported
 * in {@link HealthDTO}.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@code RARELY} → 0 points
 *   <li>{@code SOMETIMES} → 2 points
 *   <li>{@code FREQUENTLY} → 4 points
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setSleepDifficultyScore(int)}.
 *
 * @since 1.0.0
 */
@Service
public class SleepDifficultyScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toSleepDifficultyScore(healthDTO);
    healthScoreSummaryDTO.setSleepDifficultyScore(score);
  }

  private int toSleepDifficultyScore(HealthDTO healthDTO) {
    final var diff = healthDTO.sleepDifficulty();
    if (diff == null) return 0;

    return switch (diff) {
      case "RARELY" -> 0;
      case "SOMETIMES" -> 2;
      case "FREQUENTLY" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + diff);
    };
  }
}
