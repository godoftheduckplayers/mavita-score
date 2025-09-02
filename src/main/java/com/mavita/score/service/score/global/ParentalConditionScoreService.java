package com.mavita.score.service.score.global;

import com.mavita.score.exception.InvalidParentalConditionSelectionException;
import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that calculates a score based on parental chronic conditions.
 *
 * <p>Input is taken from {@link HealthDTO#parentalConditions()} (enum values). Rules:
 *
 * <ul>
 *   <li>Each selected condition (except {@code NONE}) = 5 pts;
 *   <li>{@code NONE} alone = 0 pts;
 *   <li>If {@code NONE} is combined with any other condition, an {@link
 *       InvalidParentalConditionSelectionException} is thrown.
 * </ul>
 *
 * <p>The result is written into {@link HealthScoreSummaryDTO#setParentalConditionsScore(int)}. This
 * method performs no I/O and mutates the provided summary in place.
 *
 * <p><b>Note:</b> {@link ProfileDTO} is part of the unified contract but not used in this rule.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class ParentalConditionScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final List<String> conditions = healthDTO.personalFamilyHistory().parentalConditions();
    final int score = toParentalConditionScore(conditions);

    healthScoreSummaryDTO.setParentalConditionsScore(score);
  }

  private int toParentalConditionScore(List<String> conditions) {
    if (conditions == null || conditions.isEmpty()) {
      return 0;
    }

    final boolean hasNone = conditions.contains("NONE");
    if (hasNone && conditions.size() > 1) {
      throw new InvalidParentalConditionSelectionException(
          "'NONE' must not be selected with other conditions");
    }

    return hasNone ? 0 : conditions.size() * 5;
  }
}
