package tyler.gymmatch.gymmatch.gym;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import tyler.gymmatch.gymmatch.base.BaseEntity;

@Entity
@Table(name = "gyms")
public class Gym extends BaseEntity {

  @Id
  @Column(length = 50, nullable = false, updatable = false)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  private String phone;
  private String email;
  private String website;

  @Column(length = 2000)
  private String description;

  protected Gym() {}

  public Gym(
      String name, String address, String phone, String email, String website, String description) {
    this.id = "Gym_" + UUID.randomUUID();
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.website = website;
    this.description = description;

  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getWebsite() {
    return website;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
