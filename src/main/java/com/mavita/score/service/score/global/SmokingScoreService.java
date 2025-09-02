package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates a score based on smoking status reported in {@link
 * HealthDTO}.
 *
 * <p>Scoring rule:
 *
 * <ul>
 *   <li>{@code true} (smokes) &rarr; 4 points
 *   <li>{@code false} or {@code null} (does not smoke / unknown) &rarr; 0 points
 * </ul>
 *
 * <p>The result is written into {@link HealthScoreSummaryDTO#setSmokingScore(int)}.
 *
 * @since 1.0.0
 */
@Service
public class SmokingScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final int score = toSmokingScore(healthDTO.smokes());
    healthScoreSummaryDTO.setSmokingScore(score);
  }

  private int toSmokingScore(Boolean smokes) {
    return Boolean.TRUE.equals(smokes) ? 4 : 0;
  }
}
