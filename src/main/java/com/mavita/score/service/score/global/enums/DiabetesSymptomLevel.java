package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.DiabetesSymptomScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing symptoms related to diabetes (thirst, blurred vision, tingling).
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@link #NO} - 0 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #YES} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO}. Scored by {@link DiabetesSymptomScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum DiabetesSymptomLevel {
  NO,
  SOMETIMES,
  YES
}
