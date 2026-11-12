package tyler.gymmatch.gymmatch.user;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tyler.gymmatch.gymmatch.base.BaseEntity;
import tyler.gymmatch.gymmatch.gym.Gym;
import tyler.gymmatch.gymmatch.user.enums.UserEnums;
import tyler.gymmatch.gymmatch.membership.UserGym;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Id
  @Column(nullable = false, length = 50, updatable = false)
  private String id;

  @Column(nullable = false, unique = true)
  private String account;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @Column(nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column()
  private UserEnums.Gender gender;

  private LocalDate birthday;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(nullable = false)
  private UserEnums.Role role;

  private Instant lastLogin;

  @OneToMany(mappedBy = "user")
  private List<UserGym> userGyms = new ArrayList<>();

  public List<UserGym> getUserGyms() {
    return userGyms;
  }

  protected User() {
  }

  public User(
      String account,
      String passwordHash,
      String name,
      UserEnums.Gender gender,
      LocalDate birthday) {
    this.id = "User_" + UUID.randomUUID();
    this.account = account;
    this.passwordHash = passwordHash;
    this.name = name;
    this.birthday = birthday;
    this.gender = gender;
    this.role = UserEnums.Role.USER;
  }

  public void recordLogin() {
    this.lastLogin = Instant.now();
  }

  public String getId() {
    return id;
  }

  public String getAccount() {
    return account;
  }

  public String getName() {
    return name;
  }

  public UserEnums.Gender getGender() {
    return gender;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public UserEnums.Role getRole() {
    return role;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGender(UserEnums.Gender gender) {
    this.gender = gender;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public void setRole(UserEnums.Role role) {
    this.role = role;
  }

  public UserGym addGym(Gym gym) {
    UserGym userGym = new UserGym(this, gym);
    this.userGyms.add(userGym);
    return userGym;
  }
}
