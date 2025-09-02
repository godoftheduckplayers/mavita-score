package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Builds the "Mental Health" indicator from atomic domain scores contained in {@link
 * HealthScoreSummaryDTO}.
 *
 * <p><strong>Aggregation (risk points, capped at {@value #MAX_SCORE})</strong>: anxiety/shortness
 * of breath + stress level + sadness/low motivation.
 *
 * <p><strong>Progress mapping</strong>: the gauge expects 0=bad and MAX=good, so {@code progress =
 * MAX - totalRisk}.
 *
 * <p><strong>Ranges (progress domain, MAX=12)</strong> (mirrored from the original risk ranges):
 *
 * <ul>
 *   <li>9–12: Boa
 *   <li>5–8: Atenção
 *   <li>0–4: Alto risco emocional
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class MentalHealthPointerService implements PointerService {

  private static final int MAX_SCORE = 12;

  @Override
  public IndicatorScoreDTO calculate(HealthScoreSummaryDTO summary) {
    Objects.requireNonNull(summary, "healthScoreSummaryDTO must not be null");

    int total =
        summary.getAnxietyShortnessBreathScore()
            + summary.getStressLevelScore()
            + summary.getSadnessLevelScore();

    if (total < 0) total = 0;
    if (total > MAX_SCORE) total = MAX_SCORE;

    int progress = MAX_SCORE - total;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(9, 12, "#5CB85C", "boa saúde mental"),
            new IndicatorScoreDTO.Range(5, 8, "#F0AD4E", "atenção moderada"),
            new IndicatorScoreDTO.Range(0, 4, "#D9534F", "alto risco emocional"));

    return new IndicatorScoreDTO(
        "mental-health", "Saúde Mental", false, progress, MAX_SCORE, ranges, Instant.now());
  }
}
