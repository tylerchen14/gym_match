package tyler.gymmatch.gymmatch.gym;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tyler.gymmatch.gymmatch.gym.dto.GymRequest;
import tyler.gymmatch.gymmatch.gym.dto.GymResponse;

@Service
public class GymService {

  private final GymRepository gymRepository;
  private final GymMapper gymMapper;

  public GymService(GymRepository gymRepository, GymMapper gymMapper) {
    this.gymRepository = gymRepository;
    this.gymMapper = gymMapper;
  }

  public List<GymResponse> get(GymRequest.GET data) {

    Specification<Gym> spec =
        (root, query, cb) -> {
          List<Predicate> conditions = new ArrayList<>();

          if (data.id() != null && !data.id().isBlank()) {
            conditions.add(cb.equal(root.get("id"), data.id()));
          }

          if (data.name() != null && !data.name().isBlank()) {
            conditions.add(cb.like(root.get("name"), "%" + data.name().trim() + "%"));
          }

          return cb.and(conditions.toArray(new Predicate[0]));
        };

    return gymRepository.findAll(spec).stream().map(gymMapper::toResponse).toList();
  }

  @Transactional
  public GymResponse post(GymRequest.POST data) {
    Gym item =
        new Gym(
            data.name(),
            data.address(),
            data.phone(),
            data.email(),
            data.website(),
            data.description());

    Gym saved = gymRepository.save(item);

    return gymMapper.toResponse(saved);
  }

  @Transactional
  public GymResponse patch(String gymId, GymRequest.PATCH data) {
    Gym gym =
        gymRepository
            .findById(gymId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到健身房"));

    gymMapper.update(data, gym);

    return gymMapper.toResponse(gym);
  }

  @Transactional
  public void delete(String gymId) {
    Gym gym =
        gymRepository
            .findById(gymId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到健身房"));

    gymRepository.delete(gym);
  }
}
