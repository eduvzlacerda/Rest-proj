package com.example.rest_proj.repository;

import com.example.rest_proj.Entity.UserEntity;
import com.example.rest_proj.model.DTO.UserResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String username);
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByEmailVerificationToken(String token);
}
