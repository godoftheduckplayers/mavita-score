package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.NightAwakeningScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing the frequency of waking up more than twice per night.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@link #RARELY} - 0 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #ALMOST_EVERY_DAY} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO} and scored by {@link NightAwakeningScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum NightAwakeningFrequency {
  RARELY,
  SOMETIMES,
  ALMOST_EVERY_DAY
}
