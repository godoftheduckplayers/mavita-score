package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates points based on preventive exam frequency reported in {@link
 * HealthDTO}.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@code YES} &rarr; 0 pts
 *   <li>{@code SOMETIMES} &rarr; 2 pts
 *   <li>{@code NO} &rarr; 4 pts
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setPreventiveExamFrequencyScore(int)}. This method performs no I/O
 * and mutates the provided summary in place.
 *
 * @since 1.0.0
 */
@Service
public class PreventiveExamScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toPreventiveExamScore(healthDTO);
    healthScoreSummaryDTO.setPreventiveExamFrequencyScore(score);
  }

  private int toPreventiveExamScore(HealthDTO healthDTO) {
    final var freq = healthDTO.preventiveExamFrequency();
    if (freq == null) return 0;

    return switch (freq) {
      case "YES" -> 0;
      case "SOMETIMES" -> 2;
      case "NO" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + freq);
    };
  }
}
