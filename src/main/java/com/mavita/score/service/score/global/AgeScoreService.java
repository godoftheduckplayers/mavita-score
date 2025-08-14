package com.mavita.score.service.score.global;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Service;

/**
 * ScoreService implementation that calculates a score based on the user's age.
 *
 * <p>This service uses the birthdate provided in the {@link HealthDataDTO} to determine the current
 * age in years and assigns a corresponding score to the {@link HealthScoreSummaryDTO}.
 *
 * <p>Scoring logic is as follows:
 *
 * <ul>
 *   <li>Below 30 years: 0 points
 *   <li>30–39 years: 1 point
 *   <li>40–49 years: 2 points
 *   <li>50–59 years: 3 points
 *   <li>60 years and above: 4 points
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
public class AgeScoreService implements ScoreService {

  @Override
  public void calculateScore(
      HealthDataDTO healthDataDTO, HealthScoreSummaryDTO healthScoreSummaryDTO) {

    int age = Period.between(healthDataDTO.birthDate(), LocalDate.now()).getYears();
    int score = getScore(age);
    healthScoreSummaryDTO.setAgeScore(score);
  }

  private int getScore(int age) {
    if (age < 30) return 0;
    else if (age < 40) return 1;
    else if (age < 50) return 2;
    else if (age < 60) return 3;
    else return 4;
  }
}
