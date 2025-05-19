package ru.gb.api;


import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "auth_users")
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя не может быть пустым") @Column(name = "username")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым") @Column(name = "password") @Size(min = 6)
    private String password;

    @Email(message = "Не верный формат почты") @Column(name = "email")
    private String email;

    @ElementCollection(fetch = FetchType.EAGER) @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<PhotoSession> photoSessionsAsClient;

    @OneToMany(mappedBy = "photographer")
    private List<PhotoSession> photoSessionsAsPhotographer;

    @OneToOne(fetch = FetchType.LAZY)
    private CalendarUnit calendarUnit;



    private void setRole(Role role){
        roles.add( role );
    }
}
