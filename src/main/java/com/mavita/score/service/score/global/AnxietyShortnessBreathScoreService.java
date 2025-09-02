package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that computes a score based on anxiety or shortness of breath occurring without
 * physical effort, as provided in {@link HealthDTO}.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@code YES} &rarr; 4 points
 *   <li>{@code SOMETIMES} &rarr; 2 points
 *   <li>{@code RARELY} &rarr; 0 points
 * </ul>
 *
 * <p>Notes:
 *
 * <ul>
 *   <li>If the value is {@code null}, a neutral score (0) is applied.
 *   <li>This method mutates the provided {@link HealthScoreSummaryDTO} in place and performs no
 *       I/O.
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class AnxietyShortnessBreathScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    int score = toAnxietyShortnessBreathScore(healthDTO);
    healthScoreSummaryDTO.setAnxietyShortnessBreathScore(score);
  }

  private int toAnxietyShortnessBreathScore(HealthDTO healthDTO) {
    var value = healthDTO.anxietyShortnessBreath();
    if (value == null) return 0;

    return switch (value) {
      case "YES" -> 4;
      case "SOMETIMES" -> 2;
      case "RARELY" -> 0;
      default -> throw new IllegalStateException("Unexpected value: " + value);
    };
  }
}
