package com.example.coffee.shop.demo.service;

import com.example.coffee.shop.demo.model.dto.UserRequest;
import com.example.coffee.shop.demo.model.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse findUserById(Long id);
    List<UserResponse> findAllUser();
    UserResponse updateUser(Long id, UserRequest request);
    UserResponse deleteUser(Long id);
}
