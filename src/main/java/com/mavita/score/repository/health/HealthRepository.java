package com.mavita.score.repository.health;

import com.mavita.score.domain.health.Health;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** Spring Data repository for {@link Health}. */
@Repository
public interface HealthRepository extends MongoRepository<Health, String> {

  /**
   * Finds the health data for a specific user.
   *
   * @param userUuid the user's UUID
   * @return an optional health data document
   */
  Optional<Health> findByUserUuid(UUID userUuid);
}
