package ru.gb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.User;
import ru.gb.dto.UserRegistrationRequest;
import ru.gb.jwt.JwtUtils;
import ru.gb.service.AuthService;
import ru.gb.service.UserService;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;



@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/api/users/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest user) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User user = userService.getUserById( id ).orElse( null );
        if ( user != null )
            return ResponseEntity.ok( user );
        else return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( Map.of( "error", "Пользователь не найден" ) );
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok( userService.getAllUsers() );
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser( id, user );
            return ResponseEntity.ok( updatedUser );
        } catch (NoSuchElementException e) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( Map.of( "error", e.getMessage() ) );
        }
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser( id );
        return ResponseEntity.noContent().build();
    }
}
