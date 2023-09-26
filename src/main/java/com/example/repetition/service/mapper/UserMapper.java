package com.example.repetition.service.mapper;

import com.example.repetition.dto.UserDto;
import com.example.repetition.model.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract User entityId(UserDto userDto);


    public abstract UserDto toDto(User user);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToFromUser(UserDto userDto,  @MappingTarget User user);
}
