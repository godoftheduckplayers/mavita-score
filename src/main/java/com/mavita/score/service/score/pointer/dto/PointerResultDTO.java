package com.mavita.score.service.score.pointer.dto;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the result of multiple pointer (domain-specific) health score evaluations.
 *
 * <p>This DTO aggregates scores from various health areas such as general health, mental health,
 * sleep quality, and risk indicators for chronic conditions like obesity, diabetes, and
 * hypertension.
 *
 * <p>Each pointer consists of a calculated score and its corresponding maximum, which can be used
 * to determine the user's health status in that domain.
 *
 * <p>This class is typically returned as part of a broader health analysis summary to provide a
 * segmented view of the user's overall health profile.
 *
 * <p><b>Fields:</b>
 *
 * <ul>
 *   <li><b>generalHealthPointerScore</b>: Score for general health status
 *   <li><b>mentalHealthPointerScore</b>: Score reflecting mental well-being
 *   <li><b>sleepHealthPointerScore</b>: Score related to sleep quality and behavior
 *   <li><b>obesityRiskPointerScore</b>: Risk indicator score for obesity
 *   <li><b>diabetesRiskPointerScore</b>: Risk indicator score for diabetes
 *   <li><b>hypertensionRiskPointerScore</b>: Risk indicator score for hypertension
 *   <li><b>lifestyleRiskPointerScore</b>: Score reflecting lifestyle-related risk factors
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@AllArgsConstructor
@Setter
@Getter
public class PointerResultDTO {

  private final HealthScoreSummaryDTO globalScore;
  private PointerScoreDTO generalHealthPointerScore;
  private PointerScoreDTO mentalHealthPointerScore;
  private PointerScoreDTO sleepHealthPointerScore;
  private PointerScoreDTO obesityRiskPointerScore;
  private PointerScoreDTO diabetesRiskPointerScore;
  private PointerScoreDTO hypertensionRiskPointerScore;
  private PointerScoreDTO lifestyleRiskPointerScore;

  /**
   * Constructs a {@code PointerResultDTO} initialized with the provided health score summary.
   *
   * <p>This constructor sets the internal {@code globalScore} field to the given {@link
   * HealthScoreSummaryDTO}, encapsulating the detailed breakdown of health scores.
   *
   * @param healthScoreSummaryDTO the summary of individual health scores to initialize this result
   *     DTO
   */
  public PointerResultDTO(HealthScoreSummaryDTO healthScoreSummaryDTO) {
    this.globalScore = healthScoreSummaryDTO;
  }
}
