package tyler.gymmatch.gymmatch.gym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GymRequest {

  @Schema(name = "GymGetRequest")
  public record GET(String id, String name) {}

  @Schema(name = "GymCreateRequest")
  public record POST(
      @NotBlank String name,
      @NotBlank String address,
      String phone,
      @Email String email,
      String website,
      String description) {}

  @Schema(name = "GymUpdateRequest")
  public record PATCH(
      String name,
      String address,
      String phone,
      String email,
      String website,
      String description) {}
}
