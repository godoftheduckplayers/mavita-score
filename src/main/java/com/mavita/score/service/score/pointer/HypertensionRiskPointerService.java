package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Builds the "Hypertension Risk" indicator from atomic domain scores contained in {@link
 * HealthScoreSummaryDTO}.
 *
 * <p><strong>Aggregation (risk points, capped at {@value #MAX_SCORE})</strong>: alcohol consumption
 * + headache/dizziness + personal chronic conditions + parental conditions.
 *
 * <p><strong>Progress mapping</strong>: the gauge expects 0=bad and MAX=good, so {@code progress =
 * MAX - totalRisk}.
 *
 * <p><strong>Ranges (progress domain, MAX=18)</strong> (mirrored from the original risk ranges):
 *
 * <ul>
 *   <li>14–18: Baixo (green)
 *   <li>7–13: Moderado (amber)
 *   <li>0–6: Alto (red)
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class HypertensionRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 18;

  @Override
  public IndicatorScoreDTO calculate(HealthScoreSummaryDTO summary) {
    Objects.requireNonNull(summary, "healthScoreSummaryDTO must not be null");

    int total =
        summary.getAlcoholConsumptionScore()
            + summary.getHeadacheDizzinessLevelScore()
            + summary.getChronicConditionScore()
            + summary.getParentalConditionsScore();

    if (total < 0) total = 0;
    if (total > MAX_SCORE) total = MAX_SCORE;

    int progress = MAX_SCORE - total;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(14, 18, "#5CB85C", "baixo risco"),
            new IndicatorScoreDTO.Range(7, 13, "#F0AD4E", "moderado"),
            new IndicatorScoreDTO.Range(0, 6, "#D9534F", "alto risco"));

    return new IndicatorScoreDTO(
        "hypertension-risk",
        "Ris. Hipertensão",
        false,
        5,
        progress,
        MAX_SCORE,
        ranges,
        Instant.now());
  }
}
