package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.SadnessLevelScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing frequency of feeling sad and unmotivated.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@link #RARELY} - 0 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #YES} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO} and scored by {@link SadnessLevelScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum SadnessLevel {
  RARELY,
  SOMETIMES,
  YES
}
