package tyler.gymmatch.gymmatch.gym.dto;

import java.time.Instant;

public record GymResponse(
    String id,
    String name,
    String address,
    String phone,
    String email,
    String website,
    String description,
    Instant created_at,
    Instant updated_at,
    long userCount
) {}
