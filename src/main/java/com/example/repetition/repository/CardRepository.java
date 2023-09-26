package com.example.repetition.repository;

import com.example.repetition.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByCardIdAndDeletedAtIsNull(Integer entityId);
}
