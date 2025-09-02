package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Builds the "Sleep Health" indicator from atomic sleep-related scores contained in {@link
 * HealthScoreSummaryDTO}.
 *
 * <p><strong>Aggregation (risk points, capped at {@value #MAX_SCORE})</strong>: sleep hours + sleep
 * difficulty + night awakenings + wake-up mood.
 *
 * <p><strong>Progress mapping</strong>: the gauge expects 0 = bad and MAX = good, so {@code
 * progress = MAX - totalRisk}.
 *
 * <p><strong>Ranges (progress domain, MAX=16)</strong> mirrored from original risk bands:
 *
 * <ul>
 *   <li>11–16: Bom
 *   <li>6–10: Regular
 *   <li>0–5: Ruim
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class SleepHealthPointerService implements PointerService {

  private static final int MAX_SCORE = 16;

  @Override
  public IndicatorScoreDTO calculate(HealthScoreSummaryDTO summary) {
    Objects.requireNonNull(summary, "healthScoreSummaryDTO must not be null");

    int total =
        summary.getSleepHoursScore()
            + summary.getSleepDifficultyScore()
            + summary.getNightAwakeningFrequencyScore()
            + summary.getWakeUpMoodScore();

    if (total < 0) total = 0;
    if (total > MAX_SCORE) total = MAX_SCORE;

    int progress = MAX_SCORE - total;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(11, 16, "#5CB85C", "sono bom"),
            new IndicatorScoreDTO.Range(6, 10, "#F0AD4E", "sono regular"),
            new IndicatorScoreDTO.Range(0, 5, "#D9534F", "sono ruim"));

    return new IndicatorScoreDTO(
        "sleep-health", "Saúde do Sono", false, 2, progress, MAX_SCORE, ranges, Instant.now());
  }
}
