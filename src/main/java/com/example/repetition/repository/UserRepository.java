package com.example.repetition.repository;

import com.example.repetition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUserIdAndDeletedAtIsNull(Integer entityId);

    boolean existsByEmail(String email);
}
