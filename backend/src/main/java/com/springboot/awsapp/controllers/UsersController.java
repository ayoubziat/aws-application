package com.springboot.awsapp.controllers;

import com.springboot.awsapp.domain.dto.UserDto;
import com.springboot.awsapp.domain.entities.UserEntity;
import com.springboot.awsapp.mappers.Mapper;
import com.springboot.awsapp.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UsersController {

    private UsersService usersService;

    private Mapper<UserEntity, UserDto> mapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(
                usersService
                        .getAllUsers()
                        .stream()
                        .map(mapper::boToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(
                mapper.boToDto(
                        usersService.getUserById(UUID.fromString(userId))
                )
        );
    }

    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid UserDto newUser) {
        return new ResponseEntity<>(
                mapper.boToDto(usersService.addUser(
                        mapper.dtoToBo(newUser)
                )),
                HttpStatus.CREATED
        );
    }

    @PostMapping(
            path = "{userId}/profileImage/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> uploadUserProfileImage(
            @PathVariable("userId") UUID userId,
            @RequestParam("file") MultipartFile file
    ) {
        return new ResponseEntity<>(
                mapper.boToDto(usersService.uploadUserProfileImage(userId, file)),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "{userId}/profileImage/download")
    public ResponseEntity<byte[]> downloadUserProfileImage(
            @PathVariable("userId") UUID userId
    ) {
        return ResponseEntity.ok(
                usersService.downloadUserProfileImage(userId)
        );
    }
}
