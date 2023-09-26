package com.example.repetition.service.mapper;

import com.example.repetition.dto.CardDto;
import com.example.repetition.model.Card;
import com.example.repetition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardMapper {

    private final  UserMapper userMapper;
    private final UserService userService;

    public Card toEntity(CardDto cardDto){
        Card card = new Card();
        card.setCardName(cardDto.getCardName());
        card.setCardCode(cardDto.getCardCode());
        card.setCardNumber(cardDto.getCardNumber());
        card.setUserId(cardDto.getUserId());
        return card;
    }

    public CardDto toDto(Card card){
        return CardDto.builder()
                .cardId(card.getCardId())
                .cardName(card.getCardName())
                .cardCode(card.getCardCode())
                .cardNumber(card.getCardNumber())
                .userId(card.getUserId())
                .userDto(this.userMapper.toDto(card.getUser()))
                .build();
    }

    public CardDto toDtoWithUser(Card card) {
        return CardDto.builder()
                .cardId(card.getCardId())
                .cardName(card.getCardName())
                .cardCode(card.getCardCode())
                .cardNumber(card.getCardNumber())
                .userId(card.getUserId())
                .userDto(this.userService.get(card.getUserId()).getData())
                .build();
    }

    public void updateToCard(CardDto cardDto, Card card){
        return;
    }

}
