package tyler.gymmatch.gymmatch.membership;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tyler.gymmatch.gymmatch.membership.dto.UserGymResponse;

@Mapper(componentModel = "spring")
public interface UserGymMapper {

  @Mapping(source = "user.createdAt", target = "created_at")
  @Mapping(source = "user.updatedAt", target = "updated_at")
  @Mapping(source="user.id", target="userId")
  @Mapping(source="gym.id", target="gymId")
  UserGymResponse toResponse(UserGym usergym);
}
