package ru.gb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Schema(description = "Запрос. Регистрация нового пользователя/обновление данных пользователя")
public record UserRegistrationRequest(
        @Schema(description = "Логин")
        @NotBlank String login,
        @Schema(description = "Почта")
        @NotBlank @Email String email,
        @Schema(description = "Пароль")
        @NotBlank @Size(min = 8) String password,
        @Schema(description = "Имя пользователя")
        @NotBlank String firstName,
        @Schema(description = "Фамилия пользователя")
        @NotBlank String lastName,
        @Schema(description = "Роль пользователя, не обязательно, можно присвоить потом")
        String role) {

    public String getLogin() {
        return login;
    }

    public CharSequence getPassword() {
        return password;
    }
}