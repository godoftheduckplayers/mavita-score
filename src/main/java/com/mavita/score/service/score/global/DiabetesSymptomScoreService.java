package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that computes points based on diabetes-related symptoms reported in {@link
 * HealthDTO}.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@code NO} &rarr; 0 pts
 *   <li>{@code SOMETIMES} &rarr; 2 pts
 *   <li>{@code YES} &rarr; 4 pts
 * </ul>
 *
 * <p>If the value is {@code null}, a neutral score (0) is applied. The result is written into
 * {@link HealthScoreSummaryDTO#setDiabetesSymptomLevelScore(int)}.
 */
@Service
public class DiabetesSymptomScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toDiabetesSymptomScore(healthDTO);
    healthScoreSummaryDTO.setDiabetesSymptomLevelScore(score);
  }

  private int toDiabetesSymptomScore(HealthDTO healthDTO) {
    final String value = healthDTO.diabetesSymptomLevel();
    if (value == null) return 0;

    return switch (value) {
      case "NO" -> 0;
      case "SOMETIMES" -> 2;
      case "YES" -> 4;
      default -> throw new IllegalStateException("Unexpected value: " + value);
    };
  }
}
