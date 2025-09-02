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
 * <p><strong>Aggregation logic</strong> (capped at {@value #MAX_SCORE}):
 *
 * <ul>
 *   <li>Sleep hours score
 *   <li>Sleep difficulty score
 *   <li>Night awakening frequency score
 *   <li>Wake-up mood score
 * </ul>
 *
 * <p><strong>Ranges</strong> (0–16):
 *
 * <ul>
 *   <li>0–5: Bom
 *   <li>6–10: Regular
 *   <li>11–16: Ruim
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

    if (total > MAX_SCORE) total = MAX_SCORE;
    if (total < 0) total = 0;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(0, 5, "#5CB85C", "Bom"),
            new IndicatorScoreDTO.Range(6, 10, "#F0AD4E", "Regular"),
            new IndicatorScoreDTO.Range(11, 16, "#D9534F", "Ruim"));

    return new IndicatorScoreDTO(
        "sleep-health",
        "Saúde do Sono",
        /* primary */ false,
        total,
        MAX_SCORE,
        ranges,
        Instant.now());
  }
}
