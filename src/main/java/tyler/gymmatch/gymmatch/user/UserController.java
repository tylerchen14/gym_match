package tyler.gymmatch.gymmatch.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

import jakarta.validation.Valid;
import tyler.gymmatch.gymmatch.user.dto.UserRequest;
import tyler.gymmatch.gymmatch.user.dto.UserResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    };

    @GetMapping
    public List<UserResponse> getUser(
            @ParameterObject @ModelAttribute UserRequest.GET data) {
        return userService.get(data);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(
            @Valid @RequestBody UserRequest.POST data) {
        return userService.post(data);
    }

    @PatchMapping("/{userId}")
    public UserResponse patchUser(
            @Valid @RequestBody UserRequest.PATCH data,
            @PathVariable("userId") String userId) {
        return userService.patch(userId, data);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("userId") String userId) {
        userService.delete(userId);
    }

}
