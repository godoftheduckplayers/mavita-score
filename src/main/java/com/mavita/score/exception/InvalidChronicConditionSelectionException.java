package com.mavita.score.exception;

import com.mavita.score.service.score.global.ChronicConditionScoreService;

/**
 * Exception thrown when invalid combinations of chronic conditions are selected.
 *
 * <p>Example: selecting 'NONE' along with other conditions.
 *
 * <p>Thrown by {@link ChronicConditionScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public class InvalidChronicConditionSelectionException extends RuntimeException {

  public InvalidChronicConditionSelectionException(String message) {
    super(message);
  }
}
