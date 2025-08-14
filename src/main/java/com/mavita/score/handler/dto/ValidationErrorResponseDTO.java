package com.mavita.score.handler.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a list of field-level validation errors.
 *
 * <p>This object is typically used as the response body for failed validation requests, containing
 * the name of each field and its corresponding error message.
 *
 * <p>The list of {@link FieldErrorDTO} items is populated using the {@link #addFieldError(String,
 * String)} method, usually from within a global exception handler.
 *
 * <p><b>Example JSON Response:</b>
 *
 * <pre>{@code
 * {
 *   "fieldErrors": [
 *     { "field": "age", "message": "is required" },
 *     { "field": "weight", "message": "must be positive" }
 *   ]
 * }
 * }</pre>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public record ValidationErrorResponseDTO(List<FieldErrorDTO> fieldErrors) {

  /**
   * Adds a new field-level validation error to the response.
   *
   * @param field the name of the field with the error
   * @param message the associated validation error message
   */
  public void addFieldError(String field, String message) {
    this.fieldErrors.add(new FieldErrorDTO(field, message));
  }
}
