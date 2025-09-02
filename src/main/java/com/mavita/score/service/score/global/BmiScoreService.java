package com.mavita.score.service.score.global;

import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.profile.dto.ProfileDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates a score based on Body Mass Index (BMI).
 *
 * <p>The BMI is calculated using the formula: {@code weight / (height * height)} (kg/m²). The
 * resulting value is then mapped to a score:
 *
 * <ul>
 *   <li>BMI &lt; 18.5 &rarr; 2 points
 *   <li>18.5 ≤ BMI &lt; 25.0 &rarr; 0 points
 *   <li>25.0 ≤ BMI &lt; 30.0 &rarr; 2 points
 *   <li>BMI ≥ 30.0 &rarr; 4 points
 * </ul>
 *
 * <p>This service reads {@code weight} (kg) and {@code height} (m) from {@link ProfileDTO},
 * performs no I/O or persistence, and mutates the provided {@link HealthScoreSummaryDTO} in place.
 */
@Service
public class BmiScoreService implements ScoreService {

  @Override
  public void calculateScore(
      ProfileDTO profileDTO, HealthDTO healthDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    Objects.requireNonNull(profileDTO, "profileDTO must not be null");
    Objects.requireNonNull(healthScoreSummaryDTO, "healthScoreSummaryDTO must not be null");
    Objects.requireNonNull(healthDTO, "healthDTO must not be null");

    final Double weight = profileDTO.weight();
    final Double height = profileDTO.height();

    if (weight == null || height == null) {
      throw new IllegalArgumentException("Weight and height must not be null in profileDTO");
    }
    if (!(weight > 0.0) || !(height > 0.0)) {
      throw new IllegalArgumentException("Weight and height must be positive");
    }

    final double bmi = calculateBmi(weight, height);
    final int score = mapBmiToScore(bmi);

    healthScoreSummaryDTO.setBmiScore(score);
  }

  private double calculateBmi(double weightKg, double heightM) {
    return weightKg / (heightM * heightM);
  }

  private int mapBmiToScore(double bmi) {
    if (bmi < 18.5) return 2;
    if (bmi < 25.0) return 0;
    if (bmi < 30.0) return 2;
    return 4;
  }
}
