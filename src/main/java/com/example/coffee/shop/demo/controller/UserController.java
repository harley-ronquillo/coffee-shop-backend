package com.example.coffee.shop.demo.controller;

import com.example.coffee.shop.demo.model.dto.UserRequest;
import com.example.coffee.shop.demo.model.dto.UserResponse;
import com.example.coffee.shop.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request){
        return service.createUser(request);
    }

    @PutMapping("/$id")
    public UserResponse updateUser(@RequestParam Long id, @RequestBody UserRequest request){
        return service.updateUser(id, request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return service.findAllUser();
    }

    @GetMapping("/$id")
    public UserResponse getUserById(@RequestParam Long id){
        return service.findUserById(id);
    }

    @DeleteMapping
    public UserResponse deleteUser(@RequestParam Long id){
        return service.deleteUser(id);
    }
}
