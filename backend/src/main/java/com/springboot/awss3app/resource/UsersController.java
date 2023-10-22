package com.springboot.awss3app.resource;

import com.springboot.awss3app.model.User;
import com.springboot.awss3app.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UsersController {

    final private UsersService usersService;
    @GetMapping
    public List<User> getAllUsers() {
        return this.usersService.getAllUsers();
    }

    @GetMapping("{userId}")
    public User getUser(@PathVariable("userId") UUID userId) {
        return this.usersService.getUserById(userId);
    }

    @PostMapping(
            path = "{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserImage(
            @PathVariable("userId") UUID userId,
            @RequestParam("file") MultipartFile file
    ) {
        this.usersService.uploadUserImage(userId, file);
    }

    @GetMapping(path = "{userId}/image/download")
    public byte[] downloadUserImage(
            @PathVariable("userId") UUID userId
    ) {
        return this.usersService.downloadUserImage(userId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exc) {
        System.out.println(exc.getMessage());
        return exc.getMessage();
    }
}
