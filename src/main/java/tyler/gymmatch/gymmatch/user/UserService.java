package tyler.gymmatch.gymmatch.user;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tyler.gymmatch.gymmatch.user.dto.UserRequest;
import tyler.gymmatch.gymmatch.user.dto.UserResponse;
import tyler.gymmatch.gymmatch.user.enums.UserEnums;

@Service
public class UserService {

  private final UserRepository repo;
  private final UserMapper mapper;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository repo, UserMapper mapper, PasswordEncoder passwordEncoder) {
    this.repo = repo;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
  }

  public List<UserResponse> get(UserRequest.GET data) {

    Specification<User> spec =
        (root, query, cb) -> {
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

    User item =
        new User(
            data.account(),
            passwordEncoder.encode(data.password()),
            data.name(),
            data.gender(),
            data.birthday());

    User saved = repo.save(item);

    return mapper.toResponse(saved);
  }

  @Transactional
  public UserResponse update(String userId, UserRequest.PATCH data) {
    User user =
        repo.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到用戶"));

    if (data.account() != null && repo.existsByAccountAndIdNot(data.account(), userId)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "帳號已存在");
    }

    mapper.update(data, user);

    return mapper.toResponse(user);
  }

  @Transactional
  public UserResponse update_role(String userId, UserEnums.Role role) {
    User user =
        repo.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到用戶"));

    user.setRole(role);
    return mapper.toResponse(user);
  }

  @Transactional
  public void delete(String userId) {
    User user =
        repo.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到用戶"));

    repo.delete(user);
  }
}
