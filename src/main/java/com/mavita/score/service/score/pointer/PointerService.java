package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;

/**
 * Interface for services that calculate grouped health indicator scores (pointers).
 *
 * <p>Each implementation of this interface represents a specific health domain, such as general
 * health, sleep quality, physical activity, etc., and uses the provided {@link HealthDataDTO} to
 * compute a score and its corresponding classification.
 *
 * <p>This abstraction enables flexible use of multiple strategies through polymorphism or dynamic
 * resolution (e.g., using the Strategy pattern).
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public interface PointerService {

  /**
   * Calculates the general health pointer score by aggregating individual scores from the provided
   * HealthScoreSummaryDTO record, and updates the given PointerResultDTO.
   *
   * <p>The method sums specific health domain scores according to predefined weights to produce an
   * overall health indicator score.
   *
   * @param healthScoreSummaryDTO the record containing individual health domain scores (immutable)
   * @param pointerResultDTO the result object to be updated with the calculated general health
   *     score
   * @return the updated PointerResultDTO containing the general health pointer score
   */
  PointerResultDTO calculate(
      HealthScoreSummaryDTO healthScoreSummaryDTO, PointerResultDTO pointerResultDTO);
}
