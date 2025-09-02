package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
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
   * Computes health-related scores from the given profile and health inputs and writes the results
   * into the provided summary container.
   *
   * <p>This is a pure calculation routine: it performs no I/O or persistence. Implementations are
   * expected to:
   *
   * <ul>
   *   <li>Validate mandatory fields in {@code profileDTO} and {@code healthDTO};
   *   <li>Derive domain scores (e.g., sleep, mental health, clinical risk) and an overall score;
   *   <li>Normalize values to the scale defined by {@link HealthScoreSummaryDTO} (e.g., 0–100);
   *   <li>Populate the target summary with scores and any relevant metadata (such as update
   *       timestamps).
   * </ul>
   *
   * <p>The {@code healthScoreSummaryDTO} argument is mutated in place; a new instance is not
   * created by this method.
   *
   * @param profileDTO demographic/identity data used as part of the scoring (must not be {@code
   *     null})
   * @param healthDTO self-reported and/or measured health data used to compute the scores (must not
   *     be {@code null})
   * @param healthScoreSummaryDTO target container that will receive the calculated scores and
   *     metadata (must not be {@code null})
   * @throws NullPointerException if any argument is {@code null}
   * @throws IllegalArgumentException if the inputs are structurally invalid or fail validation
   */
  void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO);
}
