package tyler.gymmatch.gymmatch.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.criteria.Predicate;
import tyler.gymmatch.gymmatch.gym.Gym;
import tyler.gymmatch.gymmatch.gym.GymRepository;
import tyler.gymmatch.gymmatch.membership.UserGym;
import tyler.gymmatch.gymmatch.membership.UserGymRepository;
import tyler.gymmatch.gymmatch.user.dto.UserRequest;
import tyler.gymmatch.gymmatch.user.dto.UserResponse;
import tyler.gymmatch.gymmatch.user.enums.UserEnums;

@Service
public class UserService {

  private final UserRepository repo;
  private final UserMapper mapper;
  private final PasswordEncoder passwordEncoder;
  private final GymRepository gymRepo;
  private final UserGymRepository userGymRepo;

  public UserService(
      UserRepository repo,
      UserMapper mapper,
      PasswordEncoder passwordEncoder,
      GymRepository gymRepo,
      UserGymRepository userGymRepo) {
    this.repo = repo;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
    this.gymRepo = gymRepo;
    this.userGymRepo = userGymRepo;
  }

  public List<UserResponse> get(UserRequest.GET data) {

    Specification<User> spec = (root, query, cb) -> {
      List<Predicate> conditions = new ArrayList<>();

      if (data.id() != null && !data.id().isBlank()) {
        conditions.add(cb.equal(root.get("id"), data.id()));
      }

      if (data.name() != null && !data.name().isBlank()) {
        conditions.add(cb.like(root.get("name"), "%" + data.name() + "%"));
      }

      if (data.account() != null && !data.account().isBlank()) {
        conditions.add(cb.equal(root.get("account"), data.account()));
      }

      if (data.role() != null) {
        conditions.add(cb.equal(root.get("role"), data.role()));
      }

      return cb.and(conditions.toArray(new Predicate[0]));
    };

    return repo.findAll(spec).stream().map(mapper::toResponse).toList();
  }

  @Transactional
  public UserResponse create(UserRequest.POST data) {
    if (repo.existsByAccount(data.account())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "帳號已存在");
    }

    Set<String> gymIds = data.gymIds() == null ? new HashSet<>() : new HashSet<>(data.gymIds());

    List<Gym> gyms = gymRepo.findAllById(gymIds);

    Set<String> existingIds = gyms.stream().map(Gym::getId).collect(Collectors.toSet());

    Set<String> missingIds = new HashSet<>(gymIds);

    missingIds.removeAll(existingIds);

    if (!missingIds.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "找不到健身房: " + missingIds);
    }

    User item = new User(
        data.account(),
        passwordEncoder.encode(data.password()),
        data.name(),
        data.gender(),
        data.birthday());

    User saved = repo.save(item);

    List<UserGym> userGyms = gyms.stream().map(saved::addGym).toList();

    userGymRepo.saveAll(userGyms);

    return mapper.toResponse(saved);
  }

  @Transactional
  public UserResponse update(String userId, UserRequest.PATCH data) {
    User user = repo.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到用戶"));

    if (data.account() != null && repo.existsByAccountAndIdNot(data.account(), userId)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "帳號已存在");
    }

    mapper.update(data, user);

    return mapper.toResponse(user);
  }

  @Transactional
  public UserResponse update_role(String userId, UserEnums.Role role) {
    User user = repo.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到用戶"));

    user.setRole(role);
    return mapper.toResponse(user);
  }

  @Transactional
  public void delete(String userId) {
    User user = repo.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到用戶"));

    repo.delete(user);
  }

}
