package ru.gb.api;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_roles")
@Data
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleEnum name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
