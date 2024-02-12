package com.springboot.awsapp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "user_email")
        }
)
public class UserEntity {

    @Id
    private UUID userId;

    @Column(name = "username", nullable = false, columnDefinition = "TEXT")
    @NotEmpty(message = "UserName can not be empty or null")
    private String userName;

    private String userProfileImage; // S3 key path

    @Column(name = "user_email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String userEmail;

    @Column(nullable = false)
    private Integer userAge;

    public Optional<String> getUserProfileImage() {
        return Optional.ofNullable(userProfileImage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getUserProfileImage(), that.getUserProfileImage()) && Objects.equals(getUserEmail(), that.getUserEmail()) && Objects.equals(getUserAge(), that.getUserAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getUserProfileImage(), getUserEmail(), getUserAge());
    }
}
