package com.springboot.awsapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID userId = null;
    private String userName;
//    private String userProfileImage;
    private String userEmail;
    private Integer userAge;
}
