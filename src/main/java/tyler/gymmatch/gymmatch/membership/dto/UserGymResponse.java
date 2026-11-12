package tyler.gymmatch.gymmatch.membership.dto;

import java.time.Instant;

public record UserGymResponse(
    String id,
    String userId,
    String gymId,
    Instant joinedAt,
    Instant created_at,
    Instant updated_at) {}
