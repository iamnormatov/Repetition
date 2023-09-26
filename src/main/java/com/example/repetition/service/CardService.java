package com.example.repetition.service;

import com.example.repetition.dto.CardDto;
import com.example.repetition.dto.ResponseDto;
import com.example.repetition.dto.SimpleCRUD;
import com.example.repetition.model.Card;
import com.example.repetition.repository.CardRepository;
import com.example.repetition.service.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardService implements SimpleCRUD<Integer, CardDto> {

    private final CardMapper cardMapper;
    private final UserService userService;
    private final CardRepository cardRepository;

    @Override
    public ResponseDto<CardDto> create(CardDto dto) {
        if (this.userService.get(dto.getUserId()).getData() == null){
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("User is not found!")
                    .build();
        }
        try {
            Card card = this.cardMapper.toEntity(dto);
            card.setCreatedAt(LocalDateTime.now());
            this.cardRepository.save(card);
            return ResponseDto.<CardDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.cardMapper.toDtoWithUser(card))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message(String.format("Card %s while saving error!", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<CardDto> get(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card is not found!")
                    .build();
        }
        return ResponseDto.<CardDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<CardDto> update(CardDto dto, Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card is not found!")
                    .build();
        }
        try {
            Card card = optional.get();
            this.cardMapper.updateToCard(dto, card);
            card.setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<CardDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.cardMapper.toDto(card))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message(String.format("Card %s is updating error!", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<CardDto> delete(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card is not found!")
                    .build();
        }
        Card card = optional.get();
        card.setDeletedAt(LocalDateTime.now());
        this.cardRepository.save(card);
        return ResponseDto.<CardDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(card))
                .build();
    }
}
