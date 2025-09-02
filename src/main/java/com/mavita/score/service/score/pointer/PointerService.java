package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;

/**
 * Contract for services that build a single high-level (grouped) health indicator from already
 * computed atomic domain scores.
 *
 * <p>Each implementation represents a specific indicator (e.g., General Health, Sleep Health,
 * Mental Health) and derives an {@link IndicatorScoreDTO} by aggregating and/or weighting fields
 * from {@link HealthScoreSummaryDTO}. Implementations must not return {@code null}.
 *
 * <p>Typical usage:
 *
 * <pre>{@code
 * PointerService diabetesRisk = new DiabetesRiskPointerService();
 * IndicatorScoreDTO indicator = diabetesRisk.calculate(summary);
 * }</pre>
 *
 * <p>Implementations should be side effect free and thread-safe if shared.
 *
 * @since 1.0.0
 */
public interface PointerService {

  /**
   * Computes a single indicator score from the provided {@link HealthScoreSummaryDTO}.
   *
   * @param healthScoreSummaryDTO an immutable snapshot of atomic domain scores; must not be {@code
   *     null}
   * @return a non-{@code null} {@link IndicatorScoreDTO} representing the computed indicator
   */
  IndicatorScoreDTO calculate(HealthScoreSummaryDTO healthScoreSummaryDTO);
}
