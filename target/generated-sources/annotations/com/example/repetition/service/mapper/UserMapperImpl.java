package com.example.repetition.service.mapper;

import com.example.repetition.dto.UserDto;
import com.example.repetition.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-08T23:03:08+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User entityId(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDto.getUserId() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );
        user.setCreatedAt( userDto.getCreatedAt() );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.userId( user.getUserId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        return userDto.build();
    }

    @Override
    public void updateToFromUser(UserDto userDto, User user) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getUserId() != null ) {
            user.setUserId( userDto.getUserId() );
        }
        if ( userDto.getFirstName() != null ) {
            user.setFirstName( userDto.getFirstName() );
        }
        if ( userDto.getLastName() != null ) {
            user.setLastName( userDto.getLastName() );
        }
        if ( userDto.getEmail() != null ) {
            user.setEmail( userDto.getEmail() );
        }
        if ( userDto.getPassword() != null ) {
            user.setPassword( userDto.getPassword() );
        }
        if ( userDto.getUpdatedAt() != null ) {
            user.setUpdatedAt( userDto.getUpdatedAt() );
        }
    }
}
