package com.mavita.score.service.profile.dto;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO used for API input/output of the user profile.
 *
 * <p>All fields map 1:1 to the domain entity. {@code userUuid} is mandatory for addressing the
 * profile.
 *
 * <p>Example JSON:
 *
 * <pre>{@code
 * {
 *   "userUuid": "7d0a8e8f-8c1e-4b0b-9f6a-7a1a8f1c2d3e",
 *   "birthDate": "1990-05-10",
 *   "weight": 72.5,
 *   "height": 1.76,
 *   "sex": "FEMALE",
 *   "lgbtqiaStatus": "YES",
 *   "pregnancyStatus": "PLANNING"
 * }
 * }</pre>
 */
public record ProfileDTO(
    UUID userUuid,
    LocalDate birthDate,
    Double weight,
    Double height,
    String sex,
    String lgbtqiaStatus,
    String pregnancyStatus) {}
