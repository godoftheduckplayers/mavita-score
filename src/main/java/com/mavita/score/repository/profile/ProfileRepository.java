package com.mavita.score.repository.profile;

import com.mavita.score.domain.profile.Profile;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** Spring Data repository for {@link Profile}. */
@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

  /**
   * Finds a profile by the unique user UUID.
   *
   * @param userUuid user's UUID
   * @return optional profile
   */
  Optional<Profile> findByUserUuid(UUID userUuid);
}
