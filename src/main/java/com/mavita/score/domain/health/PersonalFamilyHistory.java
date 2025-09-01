package com.mavita.score.domain.health;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Embedded document that captures the user's personal and family medical history.
 *
 * <p>This object is typically nested inside a higher-level health data document (e.g., {@code
 * HealthData}) and mirrors the structure used by the frontend form. Both lists may contain either a
 * predefined condition code (e.g., {@code "DIABETES"}, {@code "HIGH_BLOOD_PRESSURE"}) or the
 * special code {@code "OTHER"}, in which case the corresponding free-text field must be provided.
 *
 * <h2>Field semantics</h2>
 *
 * <ul>
 *   <li><b>chronicConditions</b> — conditions reported for the user themself.
 *   <li><b>parentalConditions</b> — conditions reported for the user's parents.
 *   <li><b>chronicOther</b> — free-text detail required when {@code "OTHER"} appears in {@code
 *       chronicConditions}.
 *   <li><b>parentalOther</b> — free-text detail required when {@code "OTHER"} appears in {@code
 *       parentalConditions}.
 * </ul>
 *
 * <h2>Example JSON</h2>
 *
 * <pre>{@code
 * {
 *   "chronicConditions": ["DIABETES", "OTHER"],
 *   "parentalConditions": ["HIGH_BLOOD_PRESSURE"],
 *   "chronicOther": "Thyroid condition treated in 2018",
 *   "parentalOther": null
 * }
 * }</pre>
 *
 * <p><b>Validation tips (not enforced by this class):</b>
 *
 * <ul>
 *   <li>If {@code chronicConditions} contains {@code "OTHER"}, {@code chronicOther} should be
 *       non-null/non-blank.
 *   <li>If {@code parentalConditions} contains {@code "OTHER"}, {@code parentalOther} should be
 *       non-null/non-blank.
 *   <li>Lists may be empty but should not be {@code null} if you expect consistent JSON shapes.
 * </ul>
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalFamilyHistory {

  private List<String> chronicConditions;
  private List<String> parentalConditions;
  private String chronicOther;
  private String parentalOther;
}
