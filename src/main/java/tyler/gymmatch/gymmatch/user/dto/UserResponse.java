package tyler.gymmatch.gymmatch.user.dto;

import java.time.Instant;
import java.time.LocalDate;
import tyler.gymmatch.gymmatch.user.enums.UserEnums;

public record UserResponse(
    String id,
    String account,
    String name,
    UserEnums.Gender gender,
    LocalDate birthday,
    UserEnums.Role role,
    Instant created_at,
    Instant updated_at) {}
;
