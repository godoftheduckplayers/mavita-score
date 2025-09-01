package com.mavita.score.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

/**
 * JWT lightweight utilities for reading claims from a bearer token.
 *
 * <p>This class performs a non-validating decode of the JWT payload (Base64URL) in order to extract
 * simple claims like {@code sub}. It does not verify the signature. For security-sensitive flows,
 * validate the token using your Resource Server (Spring Security / JwtDecoder) before trusting its
 * content.
 */
public final class JwtUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {};

  /**
   * Extracts the {@code sub} (subject) claim as String from either a full "Bearer &lt;token&gt;"
   * header value or a raw JWT string.
   *
   * @param authHeaderOrToken "Bearer &lt;token&gt;" or "&lt;token&gt;"
   * @return Optional subject as String
   */
  public static Optional<String> extractSub(String authHeaderOrToken) {
    return extractClaimAsString(authHeaderOrToken, "sub");
  }

  /**
   * Extracts any String claim from either a bearer header or a raw token.
   *
   * @param authHeaderOrToken bearer header or token
   * @param claimName claim key (e.g., "sub", "email")
   * @return Optional String value
   */
  public static Optional<String> extractClaimAsString(String authHeaderOrToken, String claimName) {
    return decodePayload(authHeaderOrToken)
        .map(m -> m.get(claimName))
        .filter(v -> v instanceof String)
        .map(v -> (String) v)
        .filter(s -> !s.isBlank());
  }

  /**
   * Decodes the JWT payload (2nd part) into a Map (no signature verification).
   *
   * @param authHeaderOrToken bearer header or token
   * @return Optional Map of claims
   */
  public static Optional<Map<String, Object>> decodePayload(String authHeaderOrToken) {
    String token = normalizeToken(authHeaderOrToken);
    if (token == null) return Optional.empty();

    String[] parts = token.split("\\.");
    if (parts.length < 2) return Optional.empty(); // not a JWT

    try {
      byte[] json = Base64.getUrlDecoder().decode(padBase64(parts[1]));
      Map<String, Object> payload =
          MAPPER.readValue(new String(json, StandardCharsets.UTF_8), MAP_TYPE);
      return Optional.of(payload);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  private static String normalizeToken(String authHeaderOrToken) {
    if (authHeaderOrToken == null) return null;
    String s = authHeaderOrToken.trim();
    if (s.isEmpty()) return null;

    if (s.regionMatches(true, 0, "Bearer ", 0, 7)) {
      s = s.substring(7).trim();
    }
    return s.isEmpty() ? null : s;
  }

  private static String padBase64(String b64url) {
    int mod = b64url.length() % 4;
    if (mod == 0) return b64url;
    return b64url + "====".substring(mod);
  }
}
