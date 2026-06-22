package com.example.restapi.controller;

import com.example.restapi.domain.User;
import com.example.restapi.dto.UserDto;
import com.example.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {

        User user = userService.createUser(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        userService.deleteUser(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}