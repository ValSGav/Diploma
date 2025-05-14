package ru.gb.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.Role;
import ru.gb.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.gb.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Страница login.html
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute( "user", new User() );
        return "register"; // Страница register.html
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser( user );
            createdUser.setRoles( Set.of( Role.CLIENT ) );
            return ResponseEntity.status( HttpStatus.CREATED ).body( createdUser );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body( Map.of( "error", e.getMessage() ) );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getUserById( id ).orElse( null );
        if ( user != null )
            return ResponseEntity.ok( user );
        else return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( Map.of( "error", "Пользователь не найден" ) );
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok( userService.getAllUsers() );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser( id, user );
            return ResponseEntity.ok( updatedUser );
        } catch (NoSuchElementException e) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( Map.of( "error", e.getMessage() ) );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser( id );
        return ResponseEntity.noContent().build();
    }
}
