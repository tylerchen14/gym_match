package tyler.gymmatch.gymmatch.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tyler.gymmatch.gymmatch.base.BaseEntity;
import tyler.gymmatch.gymmatch.user.enums.UserEnums;

@Entity
@Table(name = "user")
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

  protected User() {}

  public User(String account, String passwordHash, String name, String gender, LocalDate birthday) {
    this.id = "User_" + UUID.randomUUID();
    this.account = account;
    this.passwordHash = passwordHash;
    this.name = name;
    this.birthday = birthday;
    this.gender = gender;
    this.role = User.USER;
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
}
