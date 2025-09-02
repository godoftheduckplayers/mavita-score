package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on how often the user feels healthy, as reported in
 * {@link HealthDTO}.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@code YES} → 0 points
 *   <li>{@code SOMETIMES} → 2 points
 *   <li>{@code RARELY} → 4 points
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setHealthFeelingScore(int)}. This method performs no I/O and mutates
 * the provided summary in place.
 *
 * @since 1.0.0
 */
@Service
public class HealthFeelingScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    int score = toHealthFeelingScore(healthDTO);
    healthScoreSummaryDTO.setHealthFeelingScore(score);
  }

  private int toHealthFeelingScore(HealthDTO healthDTO) {
    var value = healthDTO.healthFeeling();
    if (value == null) return 0;

    return switch (value) {
      case "YES" -> 0;
      case "SOMETIMES" -> 2;
      case "RARELY" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + value);
    };
  }
}
