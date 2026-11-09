package tyler.gymmatch.gymmatch.user.dto;

import tyler.gymmatch.gymmatch.user.enums.UserEnums;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotNull;

public class UserRequest {


    public record GET(
        String account,
        String name,
        UserEnums.Role role
    ){};

    public record POST(
        @NotBlank String account,
        @NotBlank String password,
        @NotBlank String name,
        @NotNull UserEnums.Gender gender,
        @Past LocalDate birthday
    ){};

    public record PATCH(
        String account,
        String name,
        UserEnums.Gender gender,
        @Past LocalDate birthday
    ){};

}

