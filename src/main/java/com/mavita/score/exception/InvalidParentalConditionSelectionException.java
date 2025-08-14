package com.mavita.score.exception;

import com.mavita.score.service.score.global.ParentalConditionScoreService;

/**
 * Exception thrown when invalid combinations of parental conditions are selected.
 *
 * <p>Example: selecting 'NONE' along with other options.
 *
 * <p>Thrown by {@link ParentalConditionScoreService}.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public class InvalidParentalConditionSelectionException extends RuntimeException {

  public InvalidParentalConditionSelectionException(String message) {
    super(message);
  }
}
