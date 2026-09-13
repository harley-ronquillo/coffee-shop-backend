package com.example.coffee.shop.demo.model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private Integer isDeleted;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deletedAt;
}
