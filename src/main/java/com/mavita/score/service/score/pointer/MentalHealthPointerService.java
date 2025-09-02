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
 * <p><strong>Aggregation logic</strong> (capped at {@value #MAX_SCORE}):
 *
 * <ul>
 *   <li>Anxiety / shortness of breath score
 *   <li>Stress level score
 *   <li>Sadness / low motivation score
 * </ul>
 *
 * <p><strong>Ranges</strong> (0–12):
 *
 * <ul>
 *   <li>0–3: Boa
 *   <li>4–7: Atenção
 *   <li>8–12: Alto risco emocional
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

    if (total > MAX_SCORE) total = MAX_SCORE;
    if (total < 0) total = 0;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(0, 3, "#5CB85C", "Boa"),
            new IndicatorScoreDTO.Range(4, 7, "#F0AD4E", "Atenção"),
            new IndicatorScoreDTO.Range(8, 12, "#D9534F", "Alto risco emocional"));

    return new IndicatorScoreDTO(
        "mental-health",
        "Saúde Mental",
        /* primary */ false,
        total,
        MAX_SCORE,
        ranges,
        Instant.now());
  }
}
