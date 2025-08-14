package com.mavita.score.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom validation annotation to ensure that the decimal portion of a {@link Double} number
 * (interpreted as minutes) does not exceed a specified maximum.
 *
 * <p>Primarily used to validate fields like average sleep duration where decimal values represent
 * minutes. For example, a value like {@code 6.75} (75 minutes) would be invalid if the limit is 59
 * minutes.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * @MaxMinutes(59)
 * private Double averageSleepHours;
 * }</pre>
 *
 * @author Leandro Marques
 * @since 1.0.0
 * @see MaxMinutesValidator
 */
@Documented
@Constraint(validatedBy = MaxMinutesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxMinutes {

  /**
   * Maximum allowed number of minutes in the decimal portion. For example, 0.59 = 59 minutes.
   *
   * @return maximum allowed minutes (default is 59)
   */
  int value() default 59;

  /** Error message to be shown when validation fails. */
  String message() default
      "The decimal part must represent no more than {value} minutes (e.g., max .59)";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
