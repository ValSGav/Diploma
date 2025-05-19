package ru.gb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.gb.api.Role;


public record UserRegistrationRequest(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        Role role) {

    public String getUsername() {
        return username;
    }

    public CharSequence getPassword() {
        return password;
    }
}