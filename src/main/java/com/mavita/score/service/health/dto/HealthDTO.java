package com.mavita.score.service.health.dto;

import java.util.UUID;

/**
 * DTO used for API input/output of the user's health data.
 *
 * <p>Example JSON:
 *
 * <pre>{@code
 * {
 *   "userUuid": "7d0a8e8f-8c1e-4b0b-9f6a-7a1a8f1c2d3e",
 *   "smokes": true,
 *   "alcoholConsumption": "WEEKENDS",
 *   "physicalActivityLevel": "OFTEN",
 *   "dietQuality": "HEALTHY",
 *   "healthFeeling": "YES",
 *   "averageSleepWindow": "BETWEEN_SIX_AND_EIGHT",
 *   "sleepDifficulty": "SOMETIMES",
 *   "nightAwakeningFrequency": "RARELY",
 *   "wakeUpMood": "FREQUENTLY",
 *   "anxietyShortnessBreath": "YES",
 *   "stressLevel": "SOMETIMES",
 *   "sadnessLevel": "NO",
 *   "personalFamilyHistory": {
 *     "chronicConditions": ["DIABETES"],
 *     "parentalConditions": ["HIGH_BLOOD_PRESSURE", "OTHER"],
 *     "chronicOther": null,
 *     "parentalOther": "Grandparent had condition X"
 *   },
 *   "diabetesSymptomLevel": "NO",
 *   "headacheDizzinessLevel": "SOMETIMES",
 *   "preventiveExamFrequency": "SOMETIMES"
 * }
 * }</pre>
 */
public record HealthDTO(
    UUID userUuid,
    Boolean smokes,
    String alcoholConsumption,
    String physicalActivityLevel,
    String dietQuality,
    String healthFeeling,
    String averageSleepWindow,
    String sleepDifficulty,
    String nightAwakeningFrequency,
    String wakeUpMood,
    String anxietyShortnessBreath,
    String stressLevel,
    String sadnessLevel,
    PersonalFamilyHistoryDTO personalFamilyHistory,
    String diabetesSymptomLevel,
    String headacheDizzinessLevel,
    String preventiveExamFrequency) {}
