package com.mavita.score.service;

import com.mavita.score.service.score.global.ScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Main service responsible for calculating the total health score.
 *
 * <p>This service delegates the calculation of individual score components (e.g., age, BMI, blood
 * pressure) to a list of {@link ScoreService} implementations. Each component is responsible for
 * evaluating a specific aspect of the user's health data.
 *
 * <p>The results from each {@code ScoreService} are accumulated into a single {@link
 * HealthScoreSummaryDTO}, which is returned as the final output.
 *
 * <p><b>Usage:</b>
 *
 * <pre>{@code
 * HealthScoreSummaryDTO summary = healthScoreService.calculateTotalScore(healthDataDTO);
 * }</pre>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class HealthScoreService {

  private final List<ScoreService> components;

  /**
   * Calculates the total health score by applying each {@link ScoreService} to the input health
   * data and aggregating their results into a summary DTO.
   *
   * @param healthDataDTO the input health data (must not be null)
   * @return a {@link HealthScoreSummaryDTO} containing scores for all evaluated components
   */
  public HealthScoreSummaryDTO calculateTotalScore(HealthDataDTO healthDataDTO) {
    HealthScoreSummaryDTO healthScoreSummaryDTO = new HealthScoreSummaryDTO();
    components.forEach(
        scoreService -> scoreService.calculateScore(healthDataDTO, healthScoreSummaryDTO));
    return healthScoreSummaryDTO;
  }
}
