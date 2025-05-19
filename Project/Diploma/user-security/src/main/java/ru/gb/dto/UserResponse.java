package ru.gb.dto;

import ru.gb.api.Role;

import java.time.Instant;

public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        Role role,
        Instant createdAt) {}