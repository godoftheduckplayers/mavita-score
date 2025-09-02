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
 * <p><strong>Aggregation logic</strong> (capped at {@value #MAX_SCORE}):
 *
 * <ul>
 *   <li>Alcohol consumption score
 *   <li>Headache / dizziness score
 *   <li>Personal chronic conditions score
 *   <li>Parental conditions score
 * </ul>
 *
 * <p><strong>Ranges</strong> (0–18):
 *
 * <ul>
 *   <li>0–4: Baixo
 *   <li>5–11: Moderado
 *   <li>12–18: Alto
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

    if (total > MAX_SCORE) total = MAX_SCORE;
    if (total < 0) total = 0;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(0, 4, "#5CB85C", "Baixo"),
            new IndicatorScoreDTO.Range(5, 11, "#F0AD4E", "Moderado"),
            new IndicatorScoreDTO.Range(12, 18, "#D9534F", "Alto"));

    return new IndicatorScoreDTO(
        "hypertension-risk",
        "Ris. Hipertensão",
        /* primary */ false,
        total,
        MAX_SCORE,
        ranges,
        Instant.now());
  }
}
