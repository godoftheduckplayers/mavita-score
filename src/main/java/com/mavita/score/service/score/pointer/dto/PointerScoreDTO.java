package com.mavita.score.service.score.pointer.dto;

/**
 * Represents the raw score and its maximum possible value for a specific health pointer.
 *
 * <p>This record is used to convey the result of a health pointer calculation, such as General
 * Health, Mental Health, or Risk Assessments. It provides both the achieved score and the maximum
 * possible score for normalization, comparison, or visualization purposes.
 *
 * @param score the score obtained by the user for the specific health domain
 * @param maxScore the maximum possible score for that domain
 * @author Leandro Marques
 * @since 1.0.0
 */
public record PointerScoreDTO(int score, int maxScore) {}
