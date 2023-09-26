package com.example.repetition.controller;

import com.example.repetition.dto.ResponseDto;
import com.example.repetition.dto.SimpleCRUD;
import com.example.repetition.dto.UserDto;
import com.example.repetition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserController implements SimpleCRUD<Integer, UserDto> {

    private final UserService userService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<UserDto> create(@RequestBody UserDto dto) {
        return this.userService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<UserDto> get(@PathVariable(name = "id") Integer entityId) {
        return this.userService.get(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<UserDto> update(@RequestBody UserDto dto, @PathVariable(name = "id") Integer entityId) {
        return this.userService.update(dto, entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<UserDto> delete(@PathVariable(name = "id") Integer entityId) {
        return this.userService.delete(entityId);
    }
}
