package com.mavita.score.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for the {@link MaxMinutes} annotation.
 *
 * <p>Ensures that a {@link Double} value's fractional part (representing minutes) does not exceed
 * the configured limit.
 *
 * <p>For example, a value of {@code 6.75} means 6 hours and 75 minutes, which would be invalid if
 * the limit is 59 minutes.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public class MaxMinutesValidator implements ConstraintValidator<MaxMinutes, Double> {

  private int maxMinutes;

  /**
   * Initializes the validator with the configured max value from the annotation.
   *
   * @param constraintAnnotation the {@link MaxMinutes} instance
   */
  @Override
  public void initialize(MaxMinutes constraintAnnotation) {
    this.maxMinutes = constraintAnnotation.value();
  }

  /**
   * Validates that the decimal portion of the value does not exceed the limit.
   *
   * @param value the value to validate
   * @param context context in which the constraint is evaluated
   * @return {@code true} if valid or {@code null}; {@code false} if over limit
   */
  @Override
  public boolean isValid(Double value, ConstraintValidatorContext context) {
    if (value == null) return true;

    double fractional = value - Math.floor(value);
    int minutes = (int) Math.round(fractional * 60);

    return minutes <= maxMinutes;
  }
}
