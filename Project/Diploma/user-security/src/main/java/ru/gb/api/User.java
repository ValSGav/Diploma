package ru.gb.api;


import jakarta.persistence.*;

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
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
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
        CLIENT,
        PHOTOGRAPHER,
        ADMIN
    }
}
