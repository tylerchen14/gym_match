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

  @Mapping(source = "createdAt", target = "created_at")
  @Mapping(source = "updatedAt", target = "updated_at")
  GymResponse toResponse(Gym gym);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  void update(GymRequest.PATCH data, @MappingTarget Gym gym);
}
