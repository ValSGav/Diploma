package ru.gb.api;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auth_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Column(name = "login")
    private String login;

    private String firstName;

    private String lastName;

    @NotBlank(message = "Пароль не может быть пустым") @Column(name = "password") @Size(min = 6)
    private String password;

    @Email(message = "Не верный формат почты") @Column(name = "email")
    private String email;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }
}
