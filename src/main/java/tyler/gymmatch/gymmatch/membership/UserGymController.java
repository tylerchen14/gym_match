package tyler.gymmatch.gymmatch.membership;

import jakarta.validation.Valid;
import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tyler.gymmatch.gymmatch.membership.dto.UserGymRequest;
import tyler.gymmatch.gymmatch.membership.dto.UserGymResponse;

@RestController
@RequestMapping("/user-gyms")
public class UserGymController {

  private final UserGymService service;

  public UserGymController(UserGymService usergymservice) {
    this.service = usergymservice;
  }
  ;

  @GetMapping
  public List<UserGymResponse> get_user_gym(
      @ParameterObject @ModelAttribute UserGymRequest.GET data) {
    return service.search(data);
  }
  ;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserGymResponse create_user_gym(@Valid @RequestBody UserGymRequest.POST data) {
    return service.create(data);
  }

  @DeleteMapping("/{usergymId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete_user_gym(@PathVariable("usergymId") String usergymId) {
    service.delete(usergymId);
  }
}
