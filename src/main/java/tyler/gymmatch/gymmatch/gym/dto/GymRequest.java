package tyler.gymmatch.gymmatch.gym.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GymRequest {

  public record GET(String id, String name) {}

  public record POST(
      @NotBlank String name,
      @NotBlank String address,
      String phone,
      @Email String email,
      String website,
      String description) {}

  public record PATCH(
      String name,
      String address,
      String phone,
      String email,
      String website,
      String description) {}
}
