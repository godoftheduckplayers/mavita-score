package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.PreventiveExamScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing how often the user does preventive medical check-ups.
 *
 * <p>Scoring:
 *
 * <ul>
 *   <li>{@link #YES} - 0 pts
 *   <li>{@link #SOMETIMES} - 2 pts
 *   <li>{@link #NO} - 4 pts
 * </ul>
 *
 * Used in {@link HealthDataDTO}. Scored by {@link PreventiveExamScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum PreventiveExamFrequency {
  YES,
  SOMETIMES,
  NO
}
