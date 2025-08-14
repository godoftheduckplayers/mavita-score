package com.mavita.score.handler.dto;

/**
 * Data Transfer Object representing a single field-level validation error.
 *
 * <p>Used inside {@link ValidationErrorResponseDTO} to describe a specific input field that failed
 * validation and the corresponding error message.
 *
 * <p><b>Example:</b>
 *
 * <pre>{@code
 * {
 *   "field": "weight",
 *   "message": "must be positive"
 * }
 * }</pre>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
public record FieldErrorDTO(String field, String message) {}
