package tyler.gymmatch.gymmatch.membership;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.criteria.Predicate;
import tyler.gymmatch.gymmatch.gym.Gym;
import tyler.gymmatch.gymmatch.gym.GymRepository;
import tyler.gymmatch.gymmatch.membership.dto.UserGymRequest;
import tyler.gymmatch.gymmatch.membership.dto.UserGymResponse;
import tyler.gymmatch.gymmatch.user.User;
import tyler.gymmatch.gymmatch.user.UserRepository;

@Service
public class UserGymService {

  private final UserGymRepository repo;
  private final UserGymMapper mapper;
  private final UserRepository userRepo;
  private final GymRepository gymRepo;

  public UserGymService(
      UserGymRepository repo,
      UserGymMapper mapper,
      UserRepository userRepo,
      GymRepository gymRepo) {
    this.repo = repo;
    this.mapper = mapper;
    this.userRepo = userRepo;
    this.gymRepo = gymRepo;
  }

  public List<UserGymResponse> search(UserGymRequest.GET data) {

    Specification<UserGym> spec = (root, query, cb) -> {
      List<Predicate> conditions = new ArrayList<>();

      if (data.id() != null && !data.id().isBlank()) {
        conditions.add(cb.equal(root.get("id"), data.id()));
      }
      ;

      if (data.userId() != null && !data.userId().isBlank()) {
        conditions.add(cb.equal(root.get("user").get("id"), data.userId()));
      }
      ;

      if (data.gymId() != null && !data.gymId().isBlank()) {
        conditions.add(cb.equal(root.get("gym").get("id"), data.gymId()));
      }
      ;

      return cb.and(conditions.toArray(new Predicate[0]));
    };

    return repo.findAll(spec).stream().map(mapper::toResponse).toList();
  };

  @Transactional
  public UserGymResponse create(UserGymRequest.POST data) {

    User user = userRepo
        .findById(data.userId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到用戶"));

    Gym gym = gymRepo
        .findById(data.gymId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到健身房"));

    UserGym item = new UserGym(user, gym);

    UserGym saved = repo.save(item);

    return mapper.toResponse(saved);
  };

  @Transactional
  public void delete(String usergymId) {
    UserGym item = repo.findById(usergymId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到關連"));

    repo.delete(item);
  };
}
