package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Builds the "General Health" indicator from atomic domain scores contained in {@link
 * HealthScoreSummaryDTO}.
 *
 * <p><strong>Aggregation</strong> (risk points, capped at {@value #MAX_SCORE}): age + BMI + health
 * feeling + chronic conditions + parental conditions.
 *
 * <p><strong>Progress mapping</strong>: the UI expects 0=bad and MAX=good. We therefore convert the
 * raw risk total to progress via {@code progress = MAX - total}.
 *
 * <p><strong>Ranges (progress domain)</strong> for MAX=62 (mirrored from the original):
 *
 * <ul>
 *   <li>50–62: Ótimo (green)
 *   <li>25–49: Atenção (amber)
 *   <li>0–24: Crítico (red)
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class GeneralHealthPointerService implements PointerService {

  private static final int MAX_SCORE = 62;

  @Override
  public IndicatorScoreDTO calculate(HealthScoreSummaryDTO summary) {
    Objects.requireNonNull(summary, "healthScoreSummaryDTO must not be null");

    int total =
        summary.getAgeScore()
            + summary.getBmiScore()
            + summary.getHealthFeelingScore()
            + summary.getChronicConditionScore()
            + summary.getParentalConditionsScore();

    if (total < 0) total = 0;
    if (total > MAX_SCORE) total = MAX_SCORE;

    int progress = MAX_SCORE - total;
    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(50, 62, "#5CB85C", "boa saúde geral"),
            new IndicatorScoreDTO.Range(25, 49, "#F0AD4E", "moderada"),
            new IndicatorScoreDTO.Range(0, 24, "#D9534F", "baixa saúde geral"));

    return new IndicatorScoreDTO(
        "general-health", "Saúde Geral", true, 0, progress, MAX_SCORE, ranges, Instant.now());
  }
}
