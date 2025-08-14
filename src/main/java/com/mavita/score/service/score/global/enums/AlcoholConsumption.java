package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.AlcoholConsumptionScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing the user's alcohol consumption habits.
 *
 * <p>This enum is used in health-related data input to assess lifestyle factors that may influence
 * a person's health score.
 *
 * <p><b>Possible values:</b>
 *
 * <ul>
 *   <li>{@link #NONE} - The user does not consume alcohol.
 *   <li>{@link #WEEKENDS} - The user consumes alcohol occasionally on weekends.
 *   <li>{@link #DAILY} - The user consumes alcohol daily.
 * </ul>
 *
 * <p>This enum is typically used in {@link HealthDataDTO} and scored by {@link
 * AlcoholConsumptionScoreService}.
 *
 * <p><b>Example JSON usage:</b>
 *
 * <pre>{@code
 * {
 *   "alcoholConsumption": "WEEKENDS"
 * }
 * }</pre>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum AlcoholConsumption {
  NONE,
  DAILY,
  WEEKENDS
}
