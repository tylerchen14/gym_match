package tyler.gymmatch.gymmatch.membership;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import tyler.gymmatch.gymmatch.base.BaseEntity;
import tyler.gymmatch.gymmatch.gym.Gym;
import tyler.gymmatch.gymmatch.user.User;

@Entity
@Table(
    name = "user_gyms",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "uk_user_gyms_user_gym",
          columnNames = {"user_id", "gym_id"})
    })
public class UserGym extends BaseEntity {

  @Id
  @Column(nullable = false, length = 50, updatable = false)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "gym_id", nullable = false, updatable = false)
  private Gym gym;

  @Column(nullable = false, updatable = false)
  private Instant joinedAt;

  protected UserGym() {}
  ;

  public UserGym(User user, Gym gym) {
    this.id = "ug_" + UUID.randomUUID();
    this.user = user;
    this.gym = gym;
    this.joinedAt = Instant.now();
  }

  public String getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Gym getGym() {
    return gym;
  }

  public Instant getJoinedAt() {
    return joinedAt;
  }
}
