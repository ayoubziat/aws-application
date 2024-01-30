package com.springboot.awsapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class UserDto {

    private final UUID userId;
    private final String username;
    private String userImageLink;
}
