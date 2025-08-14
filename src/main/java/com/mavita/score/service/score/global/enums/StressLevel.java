package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.StressLevelScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing frequency of feeling stressed.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@link #RARELY} - 0 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #YES} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO} and scored by {@link StressLevelScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum StressLevel {
  RARELY,
  SOMETIMES,
  YES
}
