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
 * <p><strong>Aggregation logic</strong> (capped at {@value #MAX_SCORE}):
 *
 * <ul>
 *   <li>BMI score
 *   <li>Physical activity score
 *   <li>Diet quality score
 *   <li>Sleep difficulty score
 *   <li>Personal chronic conditions score
 *   <li>Parental conditions score
 * </ul>
 *
 * <p><strong>Ranges</strong> (0–26):
 *
 * <ul>
 *   <li>0–8: Baixo
 *   <li>9–17: Moderado
 *   <li>18–26: Alto
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

    if (total > MAX_SCORE) total = MAX_SCORE;
    if (total < 0) total = 0;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(0, 8, "#5CB85C", "Baixo"),
            new IndicatorScoreDTO.Range(9, 17, "#F0AD4E", "Moderado"),
            new IndicatorScoreDTO.Range(18, 26, "#D9534F", "Alto"));

    return new IndicatorScoreDTO(
        "obesity-risk",
        "Risco de Obesidade",
        /* primary */ false,
        total,
        MAX_SCORE,
        ranges,
        Instant.now());
  }
}
