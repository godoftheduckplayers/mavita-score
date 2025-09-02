package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates the score based on alcohol consumption habits
 * provided in {@link HealthDTO}.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@code NONE}: 0 points
 *   <li>{@code WEEKENDS}: 2 points
 *   <li>{@code DAILY}: 4 points
 * </ul>
 *
 * <p>Notes:
 *
 * <ul>
 *   <li>If the alcohol consumption value is {@code null}, a neutral score (0) is applied.
 *   <li>This method mutates the provided {@link HealthScoreSummaryDTO} in place and performs no
 *       I/O.
 * </ul>
 */
@Service
public class AlcoholConsumptionScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toAlcoholScore(healthDTO);
    healthScoreSummaryDTO.setAlcoholConsumptionScore(score);
  }

  private int toAlcoholScore(HealthDTO healthDTO) {
    final String ac = healthDTO.alcoholConsumption();
    if (ac == null) return 0;

    return switch (ac) {
      case "NONE" -> 0;
      case "WEEKENDS" -> 2;
      case "DAILY" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + ac);
    };
  }
}
