package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing possible chronic conditions for health scoring.
 *
 * <p>Scoring: Each selected condition except NONE contributes 5 points. If ONLY {@link #NONE} is
 * selected, score is 0. Used in {@link HealthDataDTO}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum ChronicCondition {
  HIGH_BLOOD_PRESSURE,
  DIABETES,
  HIGH_CHOLESTEROL,
  OBESITY,
  OTHER,
  NONE
}
