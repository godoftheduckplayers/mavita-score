package com.mavita.score.service.score.global.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Record representing a full health assessment request, combining user profile and health data
 * inputs.
 *
 * <p>Used as a wrapper in the score evaluation endpoint to ensure all required information is
 * submitted together and validated.
 *
 * @param userProfile user's demographic and personal profile
 * @param healthData health behavior and symptom-related data
 * @author Leandro Marques
 * @since 1.0.0
 */
public record HealthAssessmentRequestDTO(
    @NotNull(message = "is required") @Valid UserProfileDTO userProfile,
    @NotNull(message = "is required") @Valid HealthDataDTO healthData) {}
