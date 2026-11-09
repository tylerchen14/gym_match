package tyler.gymmatch.gymmatch.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import tyler.gymmatch.gymmatch.user.dto.UserRequest;
import tyler.gymmatch.gymmatch.user.dto.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "createdAt", target = "created_at")
  @Mapping(source = "updatedAt", target = "updated_at")
  UserResponse toResponse(User user);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  void update(UserRequest.PATCH data, @MappingTarget User user);
}
