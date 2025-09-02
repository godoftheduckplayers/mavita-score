package com.mavita.score.service.score.pointer;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class DiabetesRiskPointerService implements PointerService {

  private static final int MAX_SCORE = 22;

  @Override
  public IndicatorScoreDTO calculate(HealthScoreSummaryDTO summary) {
    Objects.requireNonNull(summary, "healthScoreSummaryDTO must not be null");

    int rawRisk =
        summary.getBmiScore()
            + summary.getDiabetesSymptomLevelScore()
            + summary.getSleepDifficultyScore()
            + summary.getChronicConditionScore()
            + summary.getParentalConditionsScore();

    if (rawRisk < 0) rawRisk = 0;
    if (rawRisk > MAX_SCORE) rawRisk = MAX_SCORE;

    int progress = MAX_SCORE - rawRisk;

    List<IndicatorScoreDTO.Range> ranges =
        List.of(
            new IndicatorScoreDTO.Range(16, 22, "#5CB85C", "baixo risco"),
            new IndicatorScoreDTO.Range(8, 15, "#F0AD4E", "moderado"),
            new IndicatorScoreDTO.Range(0, 7, "#D9534F", "alto risco"));

    return new IndicatorScoreDTO(
        "diabetes-risk", "Risco de Diabetes", false, progress, MAX_SCORE, ranges, Instant.now());
  }
}
