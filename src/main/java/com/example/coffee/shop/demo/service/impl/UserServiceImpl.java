package com.example.coffee.shop.demo.service.impl;

import com.example.coffee.shop.demo.exception.InvalidPasswordException;
import com.example.coffee.shop.demo.exception.UserAlreadyExistsException;
import com.example.coffee.shop.demo.exception.UserNotFoundException;
import com.example.coffee.shop.demo.model.dto.UserRequest;
import com.example.coffee.shop.demo.model.dto.UserResponse;
import com.example.coffee.shop.demo.model.entity.User;
import com.example.coffee.shop.demo.repository.UserRepository;
import com.example.coffee.shop.demo.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    private boolean isPasswordValid(String password){
        if (password == null) return false;

        String regex = "\\A(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9]).{8,}\\Z";

        return password.matches(regex);
    }

    private boolean isEmailExist(String email){
        return repository.existsByEmail(email);
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        if(isEmailExist(request.getEmail())){
            throw new UserAlreadyExistsException(request.getEmail());
        }

        if(!isPasswordValid(request.getPassword()))
            throw new InvalidPasswordException();


        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        repository.save(user);
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getIsDeleted(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    @Override
    public UserResponse findUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getIsDeleted(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    @Override
    public List<UserResponse> findAllUser() {
        return repository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getIsDeleted(),
                        user.getCreatedAt(),
                        user.getUpdatedAt(),
                        user.getDeletedAt()
                ))
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This user id does not exist"));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getIsDeleted(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    @Override
    public UserResponse deleteUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setIsDeleted(1);
        user.setDeletedAt(LocalDate.now());

        repository.save(user);
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getIsDeleted(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }
}
