package ru.gb.api;


import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "auth_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Column(name = "username")
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Column(name = "password")
    private String password;
    @Email(message = "Неверный формат почты")
    @Column(name = "email")
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    // доп. поля
    @OneToMany(mappedBy = "client")
    private List<PhotoSession> photoSessionsAsClient;

    @OneToMany(mappedBy = "photographer")
    private List<PhotoSession> photoSessionsAsPhotographer;

    // геттеры, сеттеры, конструкторы


    public enum Role {
        CLIENT, PHOTOGRAPHER, ADMIN
    }
}
