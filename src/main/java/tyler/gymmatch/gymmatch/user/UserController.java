package tyler.gymmatch.gymmatch.user;

import jakarta.validation.Valid;
import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tyler.gymmatch.gymmatch.user.dto.UserRequest;
import tyler.gymmatch.gymmatch.user.dto.UserResponse;
import tyler.gymmatch.gymmatch.user.enums.UserEnums;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
  ;

  @GetMapping
  public List<UserResponse> getUser(@ParameterObject @ModelAttribute UserRequest.GET data) {
    return userService.get(data);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createUser(@Valid @RequestBody UserRequest.POST data) {
    return userService.create(data);
  }

  @PatchMapping("/{userId}")
  public UserResponse patchUser(
      @Valid @RequestBody UserRequest.PATCH data, @PathVariable("userId") String userId) {
    return userService.update(userId, data);
  }

  @PatchMapping("/{userId}/role/{role}")
  public UserResponse updateUserRole(
      @PathVariable("userId") String userId, @PathVariable("role") UserEnums.Role role) {
    return userService.update_role(userId, role);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable("userId") String userId) {
    userService.delete(userId);
  }
}
