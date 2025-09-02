package com.mavita.score.service.score.global;

import com.mavita.score.exception.InvalidChronicConditionSelectionException;
import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService that computes a score based on reported chronic conditions.
 *
 * <p>Input is taken from {@link String} (enum values). The rule is:
 *
 * <ul>
 *   <li>Each condition counts as 5 points (excluding {@code NONE});
 *   <li>If {@code NONE} is selected alone, the score is 0;
 *   <li>If {@code NONE} is combined with any other condition, an {@link
 *       InvalidChronicConditionSelectionException} is thrown.
 * </ul>
 *
 * <p>The result is written into {@link HealthScoreSummaryDTO#setChronicConditionScore(int)}. This
 * method performs no I/O and mutates the provided summary in place.
 *
 * <p><b>Note:</b> {@link ProfileDTO} is part of the unified contract but not used in this rule.
 *
 * @since 1.0.0
 */
@Service
public class ChronicConditionScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final List<String> conditions = healthDTO.personalFamilyHistory().chronicConditions();

    final int score = toChronicConditionScore(conditions);
    healthScoreSummaryDTO.setChronicConditionScore(score);
  }

  private int toChronicConditionScore(List<String> conditions) {
    if (conditions == null || conditions.isEmpty()) {
      return 0;
    }

    final boolean hasNone = conditions.contains("NONE");
    if (hasNone && conditions.size() > 1) {
      throw new InvalidChronicConditionSelectionException(
          "'NONE' must not be selected with other conditions");
    }

    return hasNone ? 0 : conditions.size() * 5;
  }
}
