package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Builds the "Obesity Risk" indicator from atomic domain scores contained in {@link
 * HealthScoreSummaryDTO}.
 *
 * <p><strong>Aggregation (risk points, capped at {@value #MAX_SCORE})</strong>: BMI + physical
 * activity + diet + sleep difficulty + personal chronic conditions + parental conditions.
 *
 * <p><strong>Progress mapping</strong>: the gauge expects 0=bad and MAX=good, so {@code progress =
 * MAX - totalRisk}.
 *
 * <p><strong>Ranges (progress domain, MAX=26)</strong> (mirrored from the original risk ranges):
 *
 * <ul>
 *   <li>18–26: Baixo
 *   <li>9–17: Moderado
 *   <li>0–8: Alto
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class ObesityRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 26;

  @Override
  public IndicatorScoreDTO calculate(HealthScoreSummaryDTO summary) {
    Objects.requireNonNull(summary, "healthScoreSummaryDTO must not be null");

    int total =
        summary.getBmiScore()
            + summary.getPhysicalActivityScore()
            + summary.getDietScore()
            + summary.getSleepDifficultyScore()
            + summary.getChronicConditionScore()
            + summary.getParentalConditionsScore();

    if (total < 0) total = 0;
    if (total > MAX_SCORE) total = MAX_SCORE;

    int progress = MAX_SCORE - total;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(18, 26, "#5CB85C", "baixo risco"),
            new IndicatorScoreDTO.Range(9, 17, "#F0AD4E", "moderado"),
            new IndicatorScoreDTO.Range(0, 8, "#D9534F", "alto risco"));

    return new IndicatorScoreDTO(
        "obesity-risk", "Risco de Obesidade", false, progress, MAX_SCORE, ranges, Instant.now());
  }
}
