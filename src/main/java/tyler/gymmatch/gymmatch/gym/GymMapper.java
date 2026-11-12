package tyler.gymmatch.gymmatch.gym;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import tyler.gymmatch.gymmatch.gym.dto.GymRequest;
import tyler.gymmatch.gymmatch.gym.dto.GymResponse;

@Mapper(componentModel = "spring")
public interface GymMapper {

  @Mapping(source = "userCount", target = "userCount")
  @Mapping(source = "gym.createdAt", target = "created_at")
  @Mapping(source = "gym.updatedAt", target = "updated_at")
  GymResponse toResponse(Gym gym, long userCount);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  void update(GymRequest.PATCH data, @MappingTarget Gym gym);
}
