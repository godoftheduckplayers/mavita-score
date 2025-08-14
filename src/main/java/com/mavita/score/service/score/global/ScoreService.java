package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;

/**
 * Interface for calculating health-related scores based on the user's input data.
 *
 * <p>Implementations of this service are responsible for analyzing health data and updating the
 * provided score summary object with the results of each evaluation.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public interface ScoreService {

  /**
   * Calculates and updates the score values based on the provided health data.
   *
   * @param healthDataDTO the input health data used for evaluation (must not be null)
   * @param healthScoreSummaryDTO the summary object where the calculated scores will be stored
   *     (must not be null)
   */
  void calculateScore(HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO);
}
