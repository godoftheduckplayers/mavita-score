package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates a score based on smoking status.
 *
 * <p>Scoring rule:
 *
 * <ul>
 *   <li>Smokes (true): 4 points
 *   <li>Does not smoke (false): 0 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class SmokingScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    Boolean smokes = healthDataDTO.smokes();
    int score = getScore(smokes);
    healthScoreSummaryDTO.setSmokingScore(score);
  }

  private int getScore(Boolean smokes) {
    return smokes ? 4 : 0;
  }
}
