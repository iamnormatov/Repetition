package com.example.repetition.controller;

import com.example.repetition.dto.CardDto;
import com.example.repetition.dto.ResponseDto;
import com.example.repetition.dto.SimpleCRUD;
import com.example.repetition.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "card")
@RequiredArgsConstructor
public class CardController implements SimpleCRUD<Integer, CardDto> {

    private final CardService cardService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<CardDto> create(@RequestBody CardDto dto) {
        return this.cardService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<CardDto> get(@PathVariable(name = "id") Integer entityId) {
        return this.cardService.get(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<CardDto> update(@RequestBody CardDto dto, @PathVariable(name = "id") Integer entityId) {
        return this.cardService.update(dto, entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<CardDto> delete(@PathVariable(name = "id") Integer entityId) {
        return this.cardService.delete(entityId);
    }
}
