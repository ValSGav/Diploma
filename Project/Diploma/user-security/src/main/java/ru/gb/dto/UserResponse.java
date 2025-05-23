package ru.gb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.gb.api.Role;

import java.time.Instant;

@Schema(description = "Ответ на запрос. Регистрация нового пользователя - данные занесенные в БД")
public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        Role role,
        Instant createdAt) {}