package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.HeadacheDizzinessScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing frequency of headaches or dizziness.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@link #NO} - 0 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #YES} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO}. Scored by {@link HeadacheDizzinessScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum HeadacheDizzinessLevel {
  NO,
  SOMETIMES,
  YES
}
