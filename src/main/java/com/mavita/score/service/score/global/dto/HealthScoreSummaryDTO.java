package com.mavita.score.service.score.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object representing the breakdown of individual scores that contribute to the
 * user's overall health assessment.
 *
 * <p>Each field corresponds to a specific health domain or behavioral factor and reflects its
 * individual contribution to the total score. These scores are typically calculated by specialized
 * services (e.g., age, BMI, smoking, etc.).
 *
 * <p>This DTO is used internally and/or externally to expose the intermediate results of the
 * scoring process, which can also be used for analytics or visualizations (e.g., radar charts,
 * score bars).
 *
 * <p><b>Fields:</b>
 *
 * <ul>
 *   <li><b>ageScore</b>: score based on user's age
 *   <li><b>bmiScore</b>: score derived from Body Mass Index (BMI)
 *   <li><b>smokingScore</b>: score reflecting tobacco consumption
 *   <li><b>alcoholConsumptionScore</b>: score based on alcohol use frequency
 *   <li><b>physicalActivityScore</b>: score based on frequency of physical activity
 *   <li><b>dietScore</b>: score based on dietary habits
 *   <li><b>healthFeelingScore</b>: score from self-perception of health
 *   <li><b>sleepHoursScore</b>: score based on average sleep duration
 *   <li><b>sleepDifficultyScore</b>: score from reported sleep difficulties
 *   <li><b>nightAwakeningFrequencyScore</b>: score based on frequency of waking at night
 *   <li><b>wakeUpMoodScore</b>: score based on how the user feels upon waking
 *   <li><b>anxietyShortnessBreathScore</b>: score from symptoms of anxiety or shortness of breath
 *   <li><b>stressLevelScore</b>: score based on perceived stress levels
 *   <li><b>sadnessLevelScore</b>: score based on feelings of sadness or lack of motivation
 *   <li><b>chronicConditionScore</b>: score based on user's personal chronic condition history
 *   <li><b>parentalConditionsScore</b>: score based on chronic conditions in user's parents
 *   <li><b>diabetesSymptomLevelScore</b>: score from symptoms suggestive of diabetes
 *   <li><b>headacheDizzinessLevelScore</b>: score based on frequency of headaches or dizziness
 *   <li><b>preventiveExamFrequencyScore</b>: score based on frequency of preventive medical
 *       checkups
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthScoreSummaryDTO {

  private int ageScore;
  private int bmiScore;
  private int smokingScore;
  private int alcoholConsumptionScore;
  private int physicalActivityScore;
  private int dietScore;
  private int healthFeelingScore;
  private int sleepHoursScore;
  private int sleepDifficultyScore;
  private int nightAwakeningFrequencyScore;
  private int wakeUpMoodScore;
  private int anxietyShortnessBreathScore;
  private int stressLevelScore;
  private int sadnessLevelScore;
  private int chronicConditionScore;
  private int parentalConditionsScore;
  private int diabetesSymptomLevelScore;
  private int headacheDizzinessLevelScore;
  private int preventiveExamFrequencyScore;
}
