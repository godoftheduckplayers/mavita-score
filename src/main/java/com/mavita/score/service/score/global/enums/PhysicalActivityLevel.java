package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.PhysicalActivityScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing the user's level of physical activity.
 *
 * <p><b>Scoring rules:</b>
 *
 * <ul>
 *   <li>{@link #ALWAYS} - 0 points
 *   <li>{@link #OFTEN} - 2 points
 *   <li>{@link #RARELY} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO} and processed by {@link PhysicalActivityScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum PhysicalActivityLevel {
  ALWAYS,
  OFTEN,
  RARELY
}
