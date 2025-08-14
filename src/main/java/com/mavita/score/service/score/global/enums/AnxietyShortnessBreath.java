package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.AnxietyShortnessBreathScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing frequency of anxiety or shortness of breath without physical effort.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@link #YES} - 4 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #RARELY} - 0 points
 * </ul>
 *
 * Used in {@link HealthDataDTO} and scored by {@link AnxietyShortnessBreathScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum AnxietyShortnessBreath {
  YES,
  SOMETIMES,
  RARELY
}
