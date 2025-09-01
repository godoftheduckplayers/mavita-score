package com.mavita.score.controller.health;

import com.mavita.score.service.health.HealthService;
import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.utils.JwtUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing the user's health data endpoints.
 *
 * <p>Endpoints:
 *
 * <ul>
 *   <li><b>PUT /api/health-data/{userUuid}</b> — create or update (upsert) the health data
 * </ul>
 *
 * <p>Examples:
 *
 * <pre>
 * PUT /api/health-data/7d0a8e8f-8c1e-4b0b-9f6a-7a1a8f1c2d3e
 * Body:
 * {
 *   "userUuid": "7d0a8e8f-8c1e-4b0b-9f6a-7a1a8f1c2d3e",
 *   "smokes": true,
 *   "alcoholConsumption": "WEEKENDS",
 *   "physicalActivityLevel": "OFTEN",
 *   "dietQuality": "HEALTHY",
 *   "healthFeeling": "YES",
 *   "averageSleepWindow": "BETWEEN_SIX_AND_EIGHT",
 *   "sleepDifficulty": "SOMETIMES",
 *   "nightAwakeningFrequency": "RARELY",
 *   "wakeUpMood": "FREQUENTLY",
 *   "anxietyShortnessBreath": "YES",
 *   "stressLevel": "SOMETIMES",
 *   "sadnessLevel": "NO",
 *   "personalFamilyHistory": {
 *     "chronicConditions": ["DIABETES"],
 *     "parentalConditions": ["HIGH_BLOOD_PRESSURE", "OTHER"],
 *     "chronicOther": null,
 *     "parentalOther": "Grandparent had condition X"
 *   },
 *   "diabetesSymptomLevel": "NO",
 *   "headacheDizzinessLevel": "SOMETIMES",
 *   "preventiveExamFrequency": "SOMETIMES"
 * }
 * </pre>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/healths")
public class HealthController {

  private final HealthService service;

  /**
   * Creates or updates (idempotent upsert) the health data for the provided {@code userUuid}.
   *
   * <p>The {@code userUuid} from the path is considered the source of truth and will overwrite any
   * different value sent in the body.
   *
   * @param body DTO containing health data fields
   * @return 200 with the saved data
   */
  @PostMapping
  public ResponseEntity<HealthDTO> upsert(
      @RequestHeader("Authorization") String authorization, @RequestBody HealthDTO body) {
    return JwtUtils.extractSub(authorization)
        .map(userUuid -> ResponseEntity.ok(service.upsert(UUID.fromString(userUuid), body)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
