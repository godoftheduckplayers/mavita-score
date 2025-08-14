package com.mavita.score.service.score.global.dto;

import com.mavita.score.service.score.global.enums.BiologicalSex;
import com.mavita.score.service.score.global.enums.LgbtqiaStatus;
import com.mavita.score.service.score.global.enums.PregnancyStatus;
import jakarta.validation.constraints.*;

/**
 * Record that encapsulates user profile data for health segmentation.
 *
 * <p>Includes: - Biological sex - LGBTQIAPN+ status - Pregnancy status or intention
 *
 * <p>Used inside {@link HealthDataDTO}.
 *
 * @param sex Biological sex (MALE/FEMALE)
 * @param lgbtqiaStatus Whether the user identifies as LGBTQIAPN+
 * @param pregnancyStatus Pregnancy status or intention
 * @author Leandro Marques
 * @since 1.0.0
 */
public record UserProfileDTO(
    @NotNull(message = "is required") BiologicalSex sex,
    LgbtqiaStatus lgbtqiaStatus,
    @NotNull(message = "is required") PregnancyStatus pregnancyStatus) {}
