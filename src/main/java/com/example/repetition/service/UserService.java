package com.example.repetition.service;

import com.example.repetition.dto.ResponseDto;
import com.example.repetition.dto.SimpleCRUD;
import com.example.repetition.dto.UserDto;
import com.example.repetition.model.User;
import com.example.repetition.repository.UserRepository;
import com.example.repetition.service.mapper.UserMapper;
import io.netty.util.internal.ObjectPool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService implements SimpleCRUD<Integer, UserDto> {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<UserDto> create(UserDto dto) {
        if (this.userRepository.existsByEmail(dto.getEmail())){
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("This email %s already exist", dto.getEmail()))
                    .build();
        }
        if (!checkEmailPattern(dto.getEmail())){
            return ResponseDto.<UserDto>builder()
                    .code(-2)
                    .message(String.format("Given %s The email was not valid", dto.getEmail()))
                    .build();
        }
        try {
            User user = this.userMapper.entityId(dto);
            user.setCreatedAt(LocalDateTime.now());
            this.userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.userMapper.toDto(user))
                    .build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message(String.format("User while %s saving error!", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> get(Integer entityId) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("User is not found!")
                    .build();
        }
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("OK")
                .data(this.userMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer entityId) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("User is not found!")
                    .build();
        }
        try {
            User user = optional.get();
            this.userMapper.updateToFromUser(dto, user);
            user.setUpdatedAt(LocalDateTime.now());
            this.userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.userMapper.toDto(user))
                    .build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message(String.format("User while %s updating error!", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> delete(Integer entityId) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("User is not found!")
                    .build();
        }
        User user = optional.get();
        user.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("OK")
                .data(this.userMapper.toDto(user))
                .build();
    }

    private boolean checkEmailPattern(String email) {
        String[] array = email.split("@");
        if (array.length == 2){
            if (array[1].equals("gmail.com")){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
