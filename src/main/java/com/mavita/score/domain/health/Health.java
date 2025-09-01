package com.mavita.score.domain.health;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document that stores the user's health data (lifestyle, sleep, mental health, etc.).
 *
 * <p>Notes:
 *
 * <ul>
 *   <li><b>userUuid</b> is unique so each user has a single health-data document.
 *   <li>Enum-like fields are kept as plain strings for maximum compatibility with the frontend.
 * </ul>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "health")
public class Health {

  @Id private String id;

  @Indexed(unique = true)
  private UUID userUuid;

  private Boolean smokes;
  private String alcoholConsumption;
  private String physicalActivityLevel;
  private String dietQuality;
  private String healthFeeling;

  private String averageSleepWindow;
  private String sleepDifficulty;
  private String nightAwakeningFrequency;
  private String wakeUpMood;

  private String anxietyShortnessBreath;
  private String stressLevel;
  private String sadnessLevel;

  private PersonalFamilyHistory personalFamilyHistory;

  private String diabetesSymptomLevel;
  private String headacheDizzinessLevel;
  private String preventiveExamFrequency;
}
