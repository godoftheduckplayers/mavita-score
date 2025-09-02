package com.mavita.score.service.score.pointer.dto;

import java.time.Instant;
import java.util.List;

/**
 * Health score indicator used by the frontend gauge cards.
 *
 * <p>Fields:
 *
 * <ul>
 *   <li><b>id</b>: unique identifier of the indicator (e.g., {@code "general-health"})
 *   <li><b>title</b>: display title (e.g., {@code "Saúde Geral"})
 *   <li><b>primary</b>: whether this indicator is the primary one
 *   <li><b>score</b>: current score value (0..maxScore)
 *   <li><b>maxScore</b>: maximum score value
 *   <li><b>ranges</b>: visual ranges for the gauge coloring/labels
 *   <li><b>updatedAt</b>: ISO-8601 UTC timestamp of the last update
 * </ul>
 *
 * <p>Example JSON:
 *
 * <pre>{@code
 * [
 *   {
 *     "id": "general-health",
 *     "title": "Saúde Geral",
 *     "primary": true,
 *     "score": 42,
 *     "maxScore": 100,
 *     "ranges": [
 *       { "from": 0, "to": 20, "color": "#5CB85C", "label": "Ótimo" },
 *       { "from": 21, "to": 60, "color": "#F0AD4E", "label": "Atenção" },
 *       { "from": 61, "to": 100, "color": "#D9534F", "label": "Crítico" }
 *     ],
 *     "updatedAt": "2025-08-14T20:34:00Z"
 *   }
 * ]
 * }</pre>
 */
public record IndicatorScoreDTO(
    String id,
    String title,
    boolean primary,
    int order,
    int score,
    int maxScore,
    List<Range> ranges,
    Instant updatedAt) {
  /**
   * Visual range for the gauge.
   *
   * <ul>
   *   <li><b>from</b>/<b>to</b>: inclusive bounds of the range
   *   <li><b>color</b>: HEX color used by the UI (e.g., {@code "#5CB85C"})
   *   <li><b>label</b>: human-readable label (e.g., {@code "Ótimo"})
   * </ul>
   */
  public record Range(int from, int to, String color, String label) {}
}
