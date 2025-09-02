package com.mavita.score.service;

import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.PointerService;
import com.mavita.score.service.score.pointer.dto.IndicatorScoreDTO;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Orchestrates the calculation of the user's health indicators (pointers).
 *
 * <p>Workflow:
 *
 * <ol>
 *   <li>Loads and computes the consolidated component scores for the user via {@link
 *       HealthScoreService#calculateTotalScore(String)}, producing a {@link HealthScoreSummaryDTO}.
 *   <li>Iterates over all registered {@link PointerService} implementations, each of which computes
 *       a domain-specific indicator and returns an {@link IndicatorScoreDTO}.
 *   <li>Collects the resulting indicators into a list and returns it to the caller.
 * </ol>
 *
 * <p>Validation & behavior:
 *
 * <ul>
 *   <li>{@code userUuid} must be non-null and non-blank; otherwise an {@link
 *       IllegalArgumentException} is thrown.
 *   <li>If the computed {@code HealthScoreSummaryDTO} is {@code null}, an empty list is returned.
 *   <li>If there are no {@code PointerService} implementations, an empty list is returned.
 *   <li>Any {@code null} returned by a {@code PointerService} is ignored.
 * </ul>
 *
 * @author Leandro Marques
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class HealthPointerScoreService {

  private final HealthScoreService healthScoreService;
  private final List<PointerService> pointerServices;

  /**
   * Calculates all health indicators for the given user.
   *
   * @param userUuid the user's UUID as a string (must not be {@code null} or blank)
   * @return a (possibly empty) list of {@link IndicatorScoreDTO} computed by all pointer services
   * @throws IllegalArgumentException if {@code userUuid} is {@code null} or blank
   */
  public List<IndicatorScoreDTO> calculateTotalScore(String userUuid) {
    if (userUuid == null || userUuid.isBlank()) {
      throw new IllegalArgumentException("userUuid must not be null or blank");
    }

    final HealthScoreSummaryDTO summary = healthScoreService.calculateTotalScore(userUuid);
    if (summary == null) {
      return List.of();
    }

    return pointerServices.stream()
        .filter(Objects::nonNull)
        .map(
            ps -> {
              try {
                return ps.calculate(summary);
              } catch (RuntimeException ex) {
                return null;
              }
            })
        .filter(Objects::nonNull)
        .sorted(Comparator.comparingInt(IndicatorScoreDTO::order))
        .collect(Collectors.toList());
  }
}
