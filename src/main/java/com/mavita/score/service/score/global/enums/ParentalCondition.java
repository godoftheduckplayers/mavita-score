package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing parental chronic conditions for scoring purposes.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>Each selected condition = 5 pts
 *   <li>NONE alone = 0 pts
 * </ul>
 *
 * Used in {@link HealthDataDTO}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum ParentalCondition {
  HIGH_BLOOD_PRESSURE,
  DIABETES,
  HIGH_CHOLESTEROL,
  OBESITY,
  OTHER,
  NONE
}
