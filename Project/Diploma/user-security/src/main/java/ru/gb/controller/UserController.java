package ru.gb.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.User;
import ru.gb.dto.UserRegistrationRequest;
import ru.gb.service.UserService;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest user) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.registerUser(user));
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
