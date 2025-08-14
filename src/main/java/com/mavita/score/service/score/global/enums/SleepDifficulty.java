package com.mavita.score.service.score.global.enums;

import com.mavita.score.service.score.global.SleepDifficultyScoreService;
import com.mavita.score.service.score.global.dto.HealthDataDTO;

/**
 * Enum representing sleep difficulty frequency.
 *
 * <p>Scoring rules:
 *
 * <ul>
 *   <li>{@link #RARELY} - 0 points
 *   <li>{@link #SOMETIMES} - 2 points
 *   <li>{@link #FREQUENTLY} - 4 points
 * </ul>
 *
 * Used in {@link HealthDataDTO} and scored by {@link SleepDifficultyScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public enum SleepDifficulty {
  RARELY,
  SOMETIMES,
  FREQUENTLY
}
