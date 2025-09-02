package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on how frequently the user wakes up more than twice
 * per night, as reported in {@link HealthDTO}.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@code RARELY} &rarr; 0 points
 *   <li>{@code SOMETIMES} &rarr; 2 points
 *   <li>{@code ALMOST_EVERY_DAY} &rarr; 4 points
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setNightAwakeningFrequencyScore(int)}.
 *
 * @since 1.0.0
 */
@Service
public class NightAwakeningScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toNightAwakeningScore(healthDTO);
    healthScoreSummaryDTO.setNightAwakeningFrequencyScore(score);
  }

  private int toNightAwakeningScore(HealthDTO healthDTO) {
    final var freq = healthDTO.nightAwakeningFrequency();
    if (freq == null) return 0;

    return switch (freq) {
      case "RARELY" -> 0;
      case "SOMETIMES" -> 2;
      case "ALMOST_EVERY_DAY" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + freq);
    };
  }
}
