package com.example.repetition.dto;

import com.example.repetition.model.User;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Integer cardId;
    private String cardName;
    private Long cardNumber;
    private Integer cardCode;
    private Integer userId;
    private UserDto userDto;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
