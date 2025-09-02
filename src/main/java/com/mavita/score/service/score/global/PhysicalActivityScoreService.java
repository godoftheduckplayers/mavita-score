package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on the user's physical activity level reported in
 * {@link HealthDTO}.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@code ALWAYS} &rarr; 0 points
 *   <li>{@code OFTEN} &rarr; 2 points
 *   <li>{@code RARELY} &rarr; 4 points
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setPhysicalActivityScore(int)}.
 *
 * @since 1.0.0
 */
@Service
public class PhysicalActivityScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toPhysicalActivityScore(healthDTO);
    healthScoreSummaryDTO.setPhysicalActivityScore(score);
  }

  private int toPhysicalActivityScore(HealthDTO healthDTO) {
    final var level = healthDTO.physicalActivityLevel();
    if (level == null) return 0;

    return switch (level) {
      case "ALWAYS" -> 0;
      case "OFTEN" -> 2;
      case "RARELY" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + level);
    };
  }
}
