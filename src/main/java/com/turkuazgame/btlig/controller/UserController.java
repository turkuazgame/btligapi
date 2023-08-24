package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.request.UserRequest;
import com.turkuazgame.btlig.response.UserResponse;
import com.turkuazgame.btlig.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<UserResponse> responseList = userService.getAllUsers();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<?> getUser(@PathVariable Long UserId) {
        UserResponse response = userService.getUser(UserId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createUser(@Valid @RequestBody UserRequest UserRequest) {
        UserResponse response = userService.createUser(UserRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{UserId}")
    public ResponseEntity<?> updateUser(@PathVariable Long UserId, @Valid @RequestBody UserRequest UserRequest) {
        UserResponse response = userService.updateUser(UserId, UserRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{UserId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long UserId) {
        userService.deleteUser(UserId);
        return ResponseEntity.ok(UserId);
    }

}
