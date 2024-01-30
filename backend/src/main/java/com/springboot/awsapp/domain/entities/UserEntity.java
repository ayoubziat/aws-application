package com.springboot.awsapp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private final UUID userId;

    private final String username;

    private String userImageLink; // S3 key path

//    public User(UUID userId, String username, String userImageLink) {
//        this.userId = userId;
//        this.username = username;
//        this.userImageLink = userImageLink;
//    }

    public Optional<String> getUserImageLink() {
        return Optional.ofNullable(userImageLink);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userImageLink, that.userImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, userImageLink);
    }
}
