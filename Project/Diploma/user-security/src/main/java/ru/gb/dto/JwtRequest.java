package ru.gb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Запрос. Авторизация пользователя")
public class JwtRequest {
    @Schema(description = "Имя пользователя")
    private String login;
    @Schema(description = "Пароль")
    private String password;
}
