package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Builds the "Lifestyle Risk" indicator from atomic lifestyle-related domain scores contained in
 * {@link HealthScoreSummaryDTO}.
 *
 * <p><strong>Aggregation logic</strong> (capped at {@value #MAX_SCORE}):
 *
 * <ul>
 *   <li>Smoking score
 *   <li>Alcohol consumption score
 *   <li>Physical activity score
 *   <li>Diet quality score
 *   <li>Sleep difficulty score
 * </ul>
 *
 * <p><strong>Ranges</strong> (0–20):
 *
 * <ul>
 *   <li>0–6: Saudável
 *   <li>7–13: Moderado
 *   <li>14–20: Alto
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class LifestyleRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 20;

  @Override
  public IndicatorScoreDTO calculate(HealthScoreSummaryDTO summary) {
    Objects.requireNonNull(summary, "healthScoreSummaryDTO must not be null");

    int total =
        summary.getSmokingScore()
            + summary.getAlcoholConsumptionScore()
            + summary.getPhysicalActivityScore()
            + summary.getDietScore()
            + summary.getSleepDifficultyScore();

    if (total > MAX_SCORE) total = MAX_SCORE;
    if (total < 0) total = 0;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(0, 6, "#5CB85C", "Saudável"),
            new IndicatorScoreDTO.Range(7, 13, "#F0AD4E", "Moderado"),
            new IndicatorScoreDTO.Range(14, 20, "#D9534F", "Alto"));

    return new IndicatorScoreDTO(
        "lifestyle-risk",
        "Estilo de Vida",
        /* primary */ false,
        total,
        MAX_SCORE,
        ranges,
        Instant.now());
  }
}
