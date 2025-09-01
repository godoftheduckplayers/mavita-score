package com.mavita.score.domain.profile;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document that stores the user's health profile fields consumed by the frontend.
 *
 * <p>Notes:
 *
 * <ul>
 *   <li><b>userUuid</b> is unique so each user has a single profile document.
 *   <li><b>birthDate</b> is kept as ISO-8601 string (e.g., "2025-08-14").
 *   <li><b>sex</b>, <b>lgbtqiaStatus</b> and <b>pregnancyStatus</b> are optional (nullable)
 *       strings.
 * </ul>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile")
public class Profile {

  @Id private String id;

  @Indexed(unique = true)
  private UUID userUuid;

  private String birthDate;

  private Double weight;

  private Double height;

  private String sex;

  private String lgbtqiaStatus;

  private String pregnancyStatus;
}
