package tyler.gymmatch.gymmatch.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import tyler.gymmatch.gymmatch.user.enums.UserEnums;

public class UserRequest {

  @Schema(name = "UserGetRequest")
  public record GET(String id, String account, String name, UserEnums.Role role) {}
  ;

  @Schema(name = "UserCreateRequest")
  public record POST(
      @Schema(example = "tyler") @NotBlank String account,
      @Schema(example = "0000") @NotBlank String password,
      @Schema(example = "小明") @NotBlank String name,
      @Schema(example = "MALE") @NotNull UserEnums.Gender gender,
      @Schema(example = "1998-05-20") @Past LocalDate birthday) {}
  ;

  @Schema(name = "UserUpdateRequest")
  public record PATCH(
      @Schema(example = "tyler_fixed") String account,
      @Schema(example = "阿多多") String name,
      @Schema(example = "OTHER") UserEnums.Gender gender,
      @Schema(example = "2018-02-20") @Past LocalDate birthday) {}
  ;
}
