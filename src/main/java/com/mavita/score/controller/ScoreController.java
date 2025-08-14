package com.mavita.score.controller;

import com.mavita.score.service.HealthPointerScoreService;
import com.mavita.score.service.HealthScoreService;
import com.mavita.score.service.score.global.dto.HealthAssessmentRequestDTO;
import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes endpoints for calculating a user's health score.
 *
 * <p>This controller receives validated input data in the form of a {@link HealthDataDTO}, and
 * delegates the score calculation to the {@link HealthScoreService}. The result is returned as a
 * {@link HealthScoreSummaryDTO}.
 *
 * <p>The endpoint expects a JSON request and responds with a JSON summary of individual health
 * scores.
 *
 * <p><b>Example Request:</b>
 *
 * <pre>{@code
 * POST /score
 * Content-Type: application/json
 *
 * {
 *   "birthDate": "1990-06-29",
 *   "weight": 70.5,
 *   "height": 1.75,
 *   ...
 * }
 * }</pre>
 *
 * <p><b>Example Response:</b>
 *
 * <pre>{@code
 * {
 *   "ageScore": 3,
 *   "bmiScore": 2,
 *   "bloodPressureScore": 1,
 *   ...
 * }
 * }</pre>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/health-score")
@RequiredArgsConstructor
public class ScoreController {

  private final HealthPointerScoreService healthPointerScoreService;

  /**
   * Endpoint to calculate the comprehensive health pointer scores based on the submitted health
   * assessment data.
   *
   * <p>Receives a {@link HealthAssessmentRequestDTO} containing raw health data via HTTP POST,
   * validates the input, and delegates to the {@code healthScoreService} to compute the overall
   * scores.
   *
   * @param healthAssessmentRequestDTO the validated request payload containing health data
   * @return a {@link PointerResultDTO} with the calculated health pointer scores
   */
  @PostMapping
  public PointerResultDTO calculateScore(
      @RequestBody @Valid HealthAssessmentRequestDTO healthAssessmentRequestDTO) {
    return healthPointerScoreService.calculateTotalScore(healthAssessmentRequestDTO.healthData());
  }
}
