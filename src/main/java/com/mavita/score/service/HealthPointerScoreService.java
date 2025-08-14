package com.mavita.score.service;

import com.mavita.score.service.score.global.dto.HealthDataDTO;
import com.mavita.score.service.score.global.dto.HealthScoreSummaryDTO;
import com.mavita.score.service.score.pointer.PointerService;
import com.mavita.score.service.score.pointer.dto.PointerResultDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for orchestrating the calculation of comprehensive health pointer scores.
 *
 * <p>This service delegates the computation of individual health scores to the {@link
 * HealthScoreService} and then aggregates these scores by invoking a set of {@link PointerService}
 * implementations. The aggregated results are encapsulated in a {@link PointerResultDTO}.
 *
 * <p>The calculation workflow is as follows:
 *
 * <ol>
 *   <li>Delegate to {@code HealthScoreService} to generate a {@link HealthScoreSummaryDTO} from raw
 *       health data input.
 *   <li>Iterate over all registered {@code PointerService} instances to calculate domain-specific
 *       pointer scores and populate a {@link PointerResultDTO}.
 * </ol>
 *
 * <p>This service acts as a central point for consolidating various health scoring strategies,
 * enabling flexible extension by adding new {@code PointerService} implementations.
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
   * Calculates the total health pointer score by combining individual domain scores.
   *
   * @param healthDataDTO the input data encapsulating raw health metrics and information
   * @return a {@link PointerResultDTO} containing the aggregated pointer scores
   */
  public PointerResultDTO calculateTotalScore(HealthDataDTO healthDataDTO) {
    HealthScoreSummaryDTO healthScoreSummaryDTO =
        healthScoreService.calculateTotalScore(healthDataDTO);
    PointerResultDTO pointerResultDTO = new PointerResultDTO(healthScoreSummaryDTO);
    pointerServices.forEach(
        pointerService -> pointerService.calculate(healthScoreSummaryDTO, pointerResultDTO));
    return pointerResultDTO;
  }
}
