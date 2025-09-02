package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on how frequently the user wakes up tired or
 * irritated, as reported in {@link HealthDTO}.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@code NO} &rarr; 0 points
 *   <li>{@code SOMETIMES} &rarr; 2 points
 *   <li>{@code FREQUENTLY} &rarr; 4 points
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setWakeUpMoodScore(int)}.
 *
 * @since 1.0.0
 */
@Service
public class WakeUpMoodScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toWakeUpMoodScore(healthDTO);
    healthScoreSummaryDTO.setWakeUpMoodScore(score);
  }

  private int toWakeUpMoodScore(HealthDTO healthDTO) {
    final var mood = healthDTO.wakeUpMood();
    if (mood == null) return 0;

    return switch (mood) {
      case "NO" -> 0;
      case "SOMETIMES" -> 2;
      case "FREQUENTLY" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + mood);
    };
  }
}
