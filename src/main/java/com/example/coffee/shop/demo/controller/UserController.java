package com.example.coffee.shop.demo.controller;

import com.example.coffee.shop.demo.model.dto.UserRequest;
import com.example.coffee.shop.demo.model.dto.UserResponse;
import com.example.coffee.shop.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        return ResponseEntity.ok()
                .body(service.createUser(request));
    }

    @PutMapping("/$id")
    public ResponseEntity<UserResponse> updateUser(@RequestParam Long id, @RequestBody UserRequest request){
        return  ResponseEntity.ok()
                .body(service.updateUser(id, request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok()
                .body(service.findAllUser());
    }

    @GetMapping("/$id")
    public ResponseEntity<UserResponse> getUserById(@RequestParam Long id){
        return ResponseEntity.ok()
                .body(service.findUserById(id));
    }

    @DeleteMapping
    public ResponseEntity<UserResponse> deleteUser(@RequestParam Long id){
        return ResponseEntity.ok()
                .body(service.deleteUser(id));
    }
}
