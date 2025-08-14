package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.WakeUpMoodScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing frequency of waking up tired or irritated.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@link #NO} - 0 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #FREQUENTLY} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO} and scored by {@link WakeUpMoodScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum WakeUpMood {
  NO,
  SOMETIMES,
  FREQUENTLY
}
