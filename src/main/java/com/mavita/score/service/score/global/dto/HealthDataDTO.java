package com.mavita.score.service.score.global.dto;

import com.mavita.score.service.score.global.enums.*;
import com.mavita.score.validator.MaxMinutes;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object representing the health-related inputs provided by a user.
 *
 * <p>This record is used to collect and validate various personal and behavioral health indicators
 * that influence the health score calculations. It includes lifestyle habits, sleep patterns,
 * mental and physical health symptoms, and chronic condition history.
 *
 * <p>All fields are required and validated to ensure consistency and completeness for health risk
 * and well-being assessment.
 *
 * @param birthDate user's date of birth
 * @param weight user's body weight in kilograms (must be positive)
 * @param height user's height in meters (must be positive)
 * @param smokes whether the user is a smoker
 * @param alcoholConsumption user's alcohol consumption habit (e.g., NONE, WEEKENDS, DAILY)
 * @param physicalActivityLevel frequency of physical activity
 * @param dietQuality subjective evaluation of dietary habits
 * @param healthFeeling user's self-assessed health perception
 * @param averageSleepHours average number of sleep hours per night (must be between 0 and 24, with
 *     up to .59 precision)
 * @param sleepDifficulty frequency of difficulty falling or staying asleep
 * @param nightAwakeningFrequency how often the user wakes up during the night
 * @param wakeUpMood typical mood or feeling upon waking (e.g., tired, irritable)
 * @param anxietyShortnessBreath presence of anxiety symptoms or shortness of breath without
 *     physical effort
 * @param stressLevel perceived stress frequency
 * @param sadnessLevel frequency of sadness or lack of motivation
 * @param chronicConditions list of user's diagnosed chronic conditions (can be multiple)
 * @param parentalConditions list of chronic conditions present in the user's parents
 * @param diabetesSymptomLevel presence of symptoms related to diabetes (e.g., excessive thirst,
 *     blurry vision)
 * @param headacheDizzinessLevel frequency of headaches or dizziness
 * @param preventiveExamFrequency how often the user undergoes preventive medical exams
 * @author Leandro Marques
 * @since 1.0.0
 */
public record HealthDataDTO(
    @NotNull(message = "is required") LocalDate birthDate,
    @NotNull(message = "is required") @Positive(message = "must be positive") Double weight,
    @NotNull(message = "is required") @Positive(message = "must be positive") Double height,
    @NotNull(message = "is required") Boolean smokes,
    @NotNull(message = "is required") AlcoholConsumption alcoholConsumption,
    @NotNull(message = "is required") PhysicalActivityLevel physicalActivityLevel,
    @NotNull(message = "is required") DietQuality dietQuality,
    @NotNull(message = "is required") HealthFeeling healthFeeling,
    @NotNull(message = "is required")
        @DecimalMin(value = "0.0", message = "cannot be negative")
        @DecimalMax(value = "24.0", message = "cannot exceed 24")
        @MaxMinutes
        Double averageSleepHours,
    @NotNull(message = "is required") SleepDifficulty sleepDifficulty,
    @NotNull(message = "is required") NightAwakeningFrequency nightAwakeningFrequency,
    @NotNull(message = "is required") WakeUpMood wakeUpMood,
    @NotNull(message = "is required") AnxietyShortnessBreath anxietyShortnessBreath,
    @NotNull(message = "is required") StressLevel stressLevel,
    @NotNull(message = "is required") SadnessLevel sadnessLevel,
    @NotEmpty(message = "can not be empty") List<ChronicCondition> chronicConditions,
    @NotEmpty(message = "can not be empty") List<ParentalCondition> parentalConditions,
    @NotNull(message = "is required") DiabetesSymptomLevel diabetesSymptomLevel,
    @NotNull(message = "is required") HeadacheDizzinessLevel headacheDizzinessLevel,
    @NotNull(message = "is required") PreventiveExamFrequency preventiveExamFrequency) {}
