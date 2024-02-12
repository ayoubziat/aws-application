package com.springboot.awsapp.repository;

import com.springboot.awsapp.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUserId(UUID userId);
}
