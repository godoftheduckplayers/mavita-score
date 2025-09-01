package com.mavita.score.service.profile;

import com.mavita.score.domain.profile.Profile;
import com.mavita.score.repository.profile.ProfileRepository;
import com.mavita.score.service.profile.dto.ProfileDTO;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application service that encapsulates reading and writing the user profile.
 *
 * <p>This service maps between the DTOs and the MongoDB document model.
 */
@RequiredArgsConstructor
@Service
public class ProfileService {

  private final ProfileRepository profileRepository;

  /**
   * Creates or updates the profile (upsert) associated to the given {@code userUuid}.
   *
   * <p>If a document already exists for this user, it is updated in-place; otherwise, a new
   * document is created.
   *
   * @param userUuid path parameter used as the source of truth for the profile ownership
   * @param payload incoming DTO (fields to be stored)
   * @return the saved DTO
   */
  @Transactional
  public ProfileDTO upsert(UUID userUuid, ProfileDTO payload) {
    Profile entity = profileRepository.findByUserUuid(userUuid).orElseGet(Profile::new);
    entity.setUserUuid(userUuid);

    entity.setBirthDate(payload.birthDate());
    entity.setWeight(payload.weight());
    entity.setHeight(payload.height());
    entity.setSex(payload.sex());
    entity.setLgbtqiaStatus(payload.lgbtqiaStatus());
    entity.setPregnancyStatus(payload.pregnancyStatus());

    return toDTO(profileRepository.save(entity));
  }

  private ProfileDTO toDTO(Profile e) {
    return new ProfileDTO(
        e.getUserUuid(),
        e.getBirthDate(),
        e.getWeight(),
        e.getHeight(),
        e.getSex(),
        e.getLgbtqiaStatus(),
        e.getPregnancyStatus());
  }
}
