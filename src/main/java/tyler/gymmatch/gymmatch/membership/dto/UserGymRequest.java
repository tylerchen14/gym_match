package tyler.gymmatch.gymmatch.membership.dto;

import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class UserGymRequest {

  @Schema(name = "UserGymGetRequest")
  public record GET(String id, String userId, String gymId) {}

  @Schema(name = "UserGymPostRequest")
  public record POST(
      @Schema(example = "User_xxx") @NotBlank String userId,
      @Schema(example = "Gym_xxx") @NotBlank String gymId,
      @Schema(example = "2026-11-11T09:00:00Z") Instant joinedAt
    ) {}
}
