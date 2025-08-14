package com.mavita.score.handler;

import static java.util.List.of;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mavita.score.exception.InvalidChronicConditionSelectionException;
import com.mavita.score.exception.InvalidParentalConditionSelectionException;
import com.mavita.score.handler.dto.ValidationErrorResponseDTO;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for processing validation errors across the application.
 *
 * <p>This class captures and formats {@link MethodArgumentNotValidException} exceptions thrown when
 * {@code @Valid} annotated request bodies fail validation.
 *
 * <p>The formatted response includes a list of field-specific error messages, which helps clients
 * identify and correct invalid input.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles validation exceptions thrown when request body fields do not satisfy the constraints
   * defined in DTO classes.
   *
   * @param ex the exception containing details about validation failures
   * @return a {@link ResponseEntity} containing a structured error response and HTTP 400 status
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponseDTO> handleValidationExceptions(
      MethodArgumentNotValidException ex) {

    ValidationErrorResponseDTO errorResponse = new ValidationErrorResponseDTO(new ArrayList<>());

    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errorResponse.addFieldError(fieldName, errorMessage);
            });

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles invalid enum values or malformed request bodies.
   *
   * @param ex the exception thrown during JSON deserialization
   * @return a structured response indicating invalid enum values
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ValidationErrorResponseDTO> handleInvalidEnumValue(
      HttpMessageNotReadableException ex) {

    Throwable cause = ex.getCause();
    ValidationErrorResponseDTO errorResponse = new ValidationErrorResponseDTO(new ArrayList<>());

    if (cause instanceof InvalidFormatException formatEx) {
      Class<?> targetType = formatEx.getTargetType();

      if (targetType.isEnum()) {
        String invalidValue = formatEx.getValue().toString();
        String enumName = targetType.getSimpleName();
        Object[] validValues = targetType.getEnumConstants();

        errorResponse.addFieldError(
            enumName,
            String.format(
                "Invalid value '%s'. Allowed values are: %s", invalidValue, of(validValues)));
      } else {
        errorResponse.addFieldError("request", "Invalid value format: " + ex.getMessage());
      }
    } else {
      errorResponse.addFieldError("request", "Malformed JSON request: " + ex.getMessage());
    }

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles all InvalidChronicConditionSelectionException thrown within controllers or services.
   *
   * @param ex the exception
   * @return a structured error response with status 400
   */
  @ExceptionHandler(InvalidChronicConditionSelectionException.class)
  public ResponseEntity<ValidationErrorResponseDTO> handleInvalidChronicConditionSelectionException(
      InvalidChronicConditionSelectionException ex) {
    ValidationErrorResponseDTO errorResponse = new ValidationErrorResponseDTO(new ArrayList<>());
    errorResponse.addFieldError("chronicConditions", ex.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  /**
   * Handles all InvalidParentalConditionSelectionException thrown within controllers or services.
   *
   * @param ex the exception
   * @return a structured error response with status 400
   */
  @ExceptionHandler(InvalidParentalConditionSelectionException.class)
  public ResponseEntity<ValidationErrorResponseDTO>
      handleInvalidParentalConditionSelectionException(
          InvalidParentalConditionSelectionException ex) {
    ValidationErrorResponseDTO errorResponse = new ValidationErrorResponseDTO(new ArrayList<>());
    errorResponse.addFieldError("parentalConditions", ex.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }
}
