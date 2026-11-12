package tyler.gymmatch.gymmatch.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import tyler.gymmatch.gymmatch.gym.dto.GymResponse;
import tyler.gymmatch.gymmatch.membership.UserGym;
import tyler.gymmatch.gymmatch.user.dto.UserRequest;
import tyler.gymmatch.gymmatch.user.dto.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "createdAt", target = "created_at")
  @Mapping(source = "updatedAt", target = "updated_at")
  @Mapping(target = "gyms", source = "userGyms")
  UserResponse toResponse(User user);

  @Mapping(target = ".", source = "gym")
  @Mapping(target = "created_at", source = "gym.createdAt")
  @Mapping(target = "updated_at", source = "gym.updatedAt")
  GymResponse toGymResponse(UserGym userGym);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  void update(UserRequest.PATCH data, @MappingTarget User user);
}
