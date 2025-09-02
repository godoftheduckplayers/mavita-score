package com.mavita.score.controller.score;

import com.mavita.score.service.HealthPointerScoreService;
import com.mavita.score.service.HealthScoreService;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import com.mavita.score.utils.JwtUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  private final HealthPointerScoreService service;

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
  public ResponseEntity<List<IndicatorScoreDTO>> getCurrentScore(
      @RequestHeader("Authorization") String authorization) {
    System.out.println(authorization);
    return JwtUtils.extractSub(authorization)
        .map(userUuid -> ResponseEntity.ok(service.calculateTotalScore(userUuid)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
