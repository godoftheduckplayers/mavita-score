package com.mavita.score.service.health.dto;

/** DTO for the embedded personal & family history data. */
public record PersonalFamilyHistoryDTO(
    java.util.List<String> chronicConditions,
    java.util.List<String> parentalConditions,
    String chronicOther,
    String parentalOther) {}
