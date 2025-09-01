package com.mavita.score.controller.profile;

import com.mavita.score.service.profile.ProfileService;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.utils.JwtUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing the User Profile CRUD operations (minimal).
 *
 * <p>Endpoints:
 *
 * <ul>
 *   <li><b>PUT /api/user-profiles/{userUuid}</b> — create or update (upsert) the profile
 * </ul>
 *
 * <p>Examples:
 *
 * <pre>
 * PUT  /api/user-profiles/7d0a8e8f-8c1e-4b0b-9f6a-7a1a8f1c2d3e
 * Body:
 * {
 *   "userUuid": "7d0a8e8f-8c1e-4b0b-9f6a-7a1a8f1c2d3e",
 *   "birthDate": "1990-05-10",
 *   "weight": 72.5,
 *   "height": 1.76,
 *   "sex": "FEMALE",
 *   "lgbtqiaStatus": "YES",
 *   "pregnancyStatus": "NO"
 * }
 * </pre>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-profiles")
public class ProfileController {

  private final ProfileService service;

  /**
   * Creates or updates the profile (idempotent) for the provided {@code userUuid}.
   *
   * <p>The {@code userUuid} from the path is considered the source of truth and will overwrite any
   * different value sent in the body.
   *
   * @param body DTO with profile fields
   * @return 200 with the saved profile
   */
  @PostMapping
  public ResponseEntity<ProfileDTO> upsert(
      @RequestHeader("Authorization") String authorization, @RequestBody ProfileDTO body) {
    return JwtUtils.extractSub(authorization)
        .map(userUuid -> ResponseEntity.ok(service.upsert(UUID.fromString(userUuid), body)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
