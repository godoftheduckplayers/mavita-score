package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates a score based on Body Mass Index (BMI).
 *
 * <p>The BMI is calculated using the formula: {@code weight / (height * height)}. The resulting
 * value is then mapped to a score based on health standards.
 *
 * <p>Scoring criteria:
 *
 * <ul>
 *   <li>BMI &lt; 18.5: 2 points
 *   <li>18.5 ≤ BMI &lt; 25.0: 0 points
 *   <li>25.0 ≤ BMI &lt; 30.0: 2 points
 *   <li>BMI ≥ 30.0: 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class BmiScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {
    double bmi = calculateBmi(healthDataDTO.weight(), healthDataDTO.height());
    int score = getScore(bmi);
    healthScoreSummaryDTO.setBmiScore(score);
  }

  private double calculateBmi(double weight, double height) {
    return weight / (height * height);
  }

  private int getScore(double bmi) {
    if (bmi < 18.5) return 2;
    else if (bmi < 25.0) return 0;
    else if (bmi < 30.0) return 2;
    else return 4;
  }
}
