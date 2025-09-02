package com.mavita.score.service.health;

import com.mavita.score.domain.health.Health;
import com.mavita.score.domain.health.PersonalFamilyHistory;
import com.mavita.score.repository.health.HealthRepository;
import com.mavita.score.service.health.dto.HealthDTO;
import com.mavita.score.service.health.dto.PersonalFamilyHistoryDTO;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application service that encapsulates reading and writing the user's health data.
 *
 * <p>Performs mapping between the DTOs and the MongoDB document model.
 */
@RequiredArgsConstructor
@Service
public class HealthService {

  private final HealthRepository healthRepository;

  /**
   * Finds the health data by the user's UUID.
   *
   * @param userUuid user's UUID
   * @return an Optional containing the DTO if found, otherwise empty
   */
  @Transactional(readOnly = true)
  public Optional<HealthDTO> findById(UUID userUuid) {
    return healthRepository.findByUserUuid(userUuid).map(this::toDTO);
  }

  /**
   * Creates or updates (idempotent upsert) the health data associated with {@code userUuid}.
   *
   * <p>If a document exists, it is updated; otherwise, a new one is created.
   *
   * @param userUuid path parameter used as the source of truth for the ownership
   * @param payload incoming DTO with the health data fields
   * @return saved DTO
   */
  @Transactional
  public HealthDTO upsert(UUID userUuid, HealthDTO payload) {
    Health entity = healthRepository.findByUserUuid(userUuid).orElseGet(Health::new);
    entity.setUserUuid(userUuid);

    entity.setSmokes(payload.smokes());
    entity.setAlcoholConsumption(payload.alcoholConsumption());
    entity.setPhysicalActivityLevel(payload.physicalActivityLevel());
    entity.setDietQuality(payload.dietQuality());
    entity.setHealthFeeling(payload.healthFeeling());

    entity.setAverageSleepWindow(payload.averageSleepWindow());
    entity.setSleepDifficulty(payload.sleepDifficulty());
    entity.setNightAwakeningFrequency(payload.nightAwakeningFrequency());
    entity.setWakeUpMood(payload.wakeUpMood());

    entity.setAnxietyShortnessBreath(payload.anxietyShortnessBreath());
    entity.setStressLevel(payload.stressLevel());
    entity.setSadnessLevel(payload.sadnessLevel());

    if (payload.personalFamilyHistory() != null) {
      PersonalFamilyHistory pfh = new PersonalFamilyHistory();
      pfh.setChronicConditions(payload.personalFamilyHistory().chronicConditions());
      pfh.setParentalConditions(payload.personalFamilyHistory().parentalConditions());
      pfh.setChronicOther(payload.personalFamilyHistory().chronicOther());
      pfh.setParentalOther(payload.personalFamilyHistory().parentalOther());
      entity.setPersonalFamilyHistory(pfh);
    } else {
      entity.setPersonalFamilyHistory(null);
    }

    entity.setDiabetesSymptomLevel(payload.diabetesSymptomLevel());
    entity.setHeadacheDizzinessLevel(payload.headacheDizzinessLevel());
    entity.setPreventiveExamFrequency(payload.preventiveExamFrequency());

    return toDTO(healthRepository.save(entity));
  }

  private HealthDTO toDTO(Health e) {
    PersonalFamilyHistoryDTO pfhDto = null;
    if (e.getPersonalFamilyHistory() != null) {
      pfhDto =
          new PersonalFamilyHistoryDTO(
              e.getPersonalFamilyHistory().getChronicConditions(),
              e.getPersonalFamilyHistory().getParentalConditions(),
              e.getPersonalFamilyHistory().getChronicOther(),
              e.getPersonalFamilyHistory().getParentalOther());
    }

    return new HealthDTO(
        e.getUserUuid(),
        e.getSmokes(),
        e.getAlcoholConsumption(),
        e.getPhysicalActivityLevel(),
        e.getDietQuality(),
        e.getHealthFeeling(),
        e.getAverageSleepWindow(),
        e.getSleepDifficulty(),
        e.getNightAwakeningFrequency(),
        e.getWakeUpMood(),
        e.getAnxietyShortnessBreath(),
        e.getStressLevel(),
        e.getSadnessLevel(),
        pfhDto,
        e.getDiabetesSymptomLevel(),
        e.getHeadacheDizzinessLevel(),
        e.getPreventiveExamFrequency());
  }
}
