package tyler.gymmatch.gymmatch.gym;

import jakarta.validation.Valid;
import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tyler.gymmatch.gymmatch.gym.dto.GymRequest;
import tyler.gymmatch.gymmatch.gym.dto.GymResponse;

@RestController
@RequestMapping("/gyms")
public class GymController {

  private final GymService gymService;

  public GymController(GymService gymService) {
    this.gymService = gymService;
  }

  @GetMapping
  public List<GymResponse> getGyms(@ParameterObject @ModelAttribute GymRequest.GET data) {
    return gymService.get(data);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GymResponse createGyms(@Valid @RequestBody GymRequest.POST data) {
    return gymService.post(data);
  }

  @PatchMapping("/{gymId}")
  public GymResponse updateGyms(
      @Valid @RequestBody GymRequest.PATCH data, @PathVariable("gymId") String gymId) {
    return gymService.patch(gymId, data);
  }

  @DeleteMapping("/{gymId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGyms(@PathVariable("gymId") String gymId) {
    gymService.delete(gymId);
  }
}
