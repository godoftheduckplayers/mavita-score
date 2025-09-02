package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that derives an age-based score from the user's profile data.
 *
 * <p>This service reads the birth date from {@link ProfileDTO} to compute the current age (in
 * years) and stores the corresponding score in {@link HealthScoreSummaryDTO}.
 *
 * <p>Scoring logic:
 *
 * <ul>
 *   <li>&lt; 30 years: 0 points
 *   <li>30–39 years: 1 point
 *   <li>40–49 years: 2 points
 *   <li>50–59 years: 3 points
 *   <li>&ge; 60 years: 4 points
 * </ul>
 *
 * <p>This method performs no I/O or persistence and mutates the provided summary in place.
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class AgeScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");

    final LocalDate birthDate = profileDTO.birthDate();
    if (birthDate == null) {
      throw new IllegalArgumentException("Birth date must not be null in profileDTO");
    }
    if (birthDate.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("Birth date cannot be in the future");
    }

    final int age = Period.between(birthDate, LocalDate.now()).getYears();
    final int score = toAgeScore(age);

    healthScoreSummaryDTO.setAgeScore(score);
  }

  private int toAgeScore(int age) {
    if (age < 30) return 0;
    if (age < 40) return 1;
    if (age < 50) return 2;
    if (age < 60) return 3;
    return 4;
  }
}
