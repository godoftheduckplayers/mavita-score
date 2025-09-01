package com.mavita.score.controller.score;

import com.mavita.score.service.HealthPointerScoreService;
import com.mavita.score.service.HealthScoreService;
import com.mavita.score.service.score.global.dto.HealthAssessmentRequestDTO;
import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

  private final HealthPointerScoreService healthPointerScoreService;

  /**
   * Returns the list of current health-score indicators for the authenticated user.
   *
   * <p>Response format example:
   *
   * <pre>{@code
   * [
   *   {
   *     "id": "general-health",
   *     "title": "Saúde Geral",
   *     "primary": true,
   *     "score": 42,
   *     "maxScore": 100,
   *     "ranges": [
   *       { "from": 0, "to": 20, "color": "#5CB85C", "label": "Ótimo" },
   *       { "from": 21, "to": 60, "color": "#F0AD4E", "label": "Atenção" },
   *       { "from": 61, "to": 100, "color": "#D9534F", "label": "Crítico" }
   *     ],
   *     "updatedAt": "2025-08-14T20:34:00Z"
   *   }
   * ]
   * }</pre>
   *
   * @return a list of {@link IndicatorScoreDTO} in the shape expected by the frontend.
   */
  @GetMapping
  public List<IndicatorScoreDTO> getCurrentScore(
      @RequestHeader("Authorization") String authorization, @RequestParam String query) {
    System.out.println(authorization);
    if ("EMPTY".equals(query)) {
      return List.of();
    }
    return List.of(
        new IndicatorScoreDTO(
            "general-health",
            "Saúde Geral",
            true,
            42,
            100,
            List.of(
                new IndicatorScoreDTO.Range(0, 20, "#5CB85C", "Ótimo"),
                new IndicatorScoreDTO.Range(21, 60, "#F0AD4E", "Atenção"),
                new IndicatorScoreDTO.Range(61, 100, "#D9534F", "Crítico")),
            Instant.parse("2025-08-14T20:34:00Z")),
        new IndicatorScoreDTO(
            "sleep-health",
            "Saúde do Sono",
            false,
            68,
            100,
            List.of(
                new IndicatorScoreDTO.Range(0, 20, "#5CB85C", "Bom"),
                new IndicatorScoreDTO.Range(21, 60, "#F0AD4E", "Regular"),
                new IndicatorScoreDTO.Range(61, 100, "#D9534F", "Ruim")),
            Instant.parse("2025-08-14T20:34:00Z")),
        new IndicatorScoreDTO(
            "diabetes-risk",
            "Risco de Diabetes",
            false,
            27,
            100,
            List.of(
                new IndicatorScoreDTO.Range(0, 20, "#5CB85C", "Baixo"),
                new IndicatorScoreDTO.Range(21, 60, "#F0AD4E", "Moderado"),
                new IndicatorScoreDTO.Range(61, 100, "#D9534F", "Alto")),
            Instant.parse("2025-08-14T20:34:00Z")),
        new IndicatorScoreDTO(
            "mental-health",
            "Saúde Mental",
            false,
            55,
            100,
            List.of(
                new IndicatorScoreDTO.Range(0, 20, "#5CB85C", "Boa"),
                new IndicatorScoreDTO.Range(21, 60, "#F0AD4E", "Atenção"),
                new IndicatorScoreDTO.Range(61, 100, "#D9534F", "Crítico")),
            Instant.parse("2025-08-14T20:34:00Z")),
        new IndicatorScoreDTO(
            "hypertension-risk",
            "Ris. Hipertensão",
            false,
            73,
            100,
            List.of(
                new IndicatorScoreDTO.Range(0, 20, "#5CB85C", "Baixo"),
                new IndicatorScoreDTO.Range(21, 60, "#F0AD4E", "Moderado"),
                new IndicatorScoreDTO.Range(61, 100, "#D9534F", "Alto")),
            Instant.parse("2025-08-14T20:34:00Z")));
  }

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
