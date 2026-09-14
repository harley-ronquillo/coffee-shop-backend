package com.example.coffee.shop.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isDeleted", nullable = false)
    private Integer isDeleted = 0;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDate updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDate.now();
    }

    public User(String name, String email, String password){
        this.name= name;
        this.email = email;
        this.password = password;
    }

}
