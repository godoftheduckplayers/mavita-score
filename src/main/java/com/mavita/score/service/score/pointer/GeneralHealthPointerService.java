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
 * <p><strong>Aggregation logic</strong> (capped at {@value #MAX_SCORE}):
 *
 * <ul>
 *   <li>Age score
 *   <li>BMI score
 *   <li>Health feeling score
 *   <li>Chronic conditions score
 *   <li>Parental conditions score
 * </ul>
 *
 * <p><strong>Ranges</strong> (proportional to max 62):
 *
 * <ul>
 *   <li>0–12: Ótimo
 *   <li>13–37: Atenção
 *   <li>38–62: Crítico
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

    if (total > MAX_SCORE) total = MAX_SCORE;
    if (total < 0) total = 0;

    // Ranges scaled to max 62 (~20% and ~60% thresholds)
    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(0, 12, "#5CB85C", "Ótimo"),
            new IndicatorScoreDTO.Range(13, 37, "#F0AD4E", "Atenção"),
            new IndicatorScoreDTO.Range(38, 62, "#D9534F", "Crítico"));

    return new IndicatorScoreDTO(
        "general-health",
        "Saúde Geral",
        /* primary */ true,
        total,
        MAX_SCORE,
        ranges,
        Instant.now());
  }
}
