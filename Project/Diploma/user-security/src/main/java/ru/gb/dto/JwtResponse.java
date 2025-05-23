package ru.gb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ на запрос. Авторизация пользователя. Два токена: для доступа и для обновления токенов")
public class JwtResponse {
    @Schema(description = "Тип авторизации")
    private final String type = "Bearer";
    @Schema(description = "Токен доступа для автризированного пользователя")
    private String accessToken;
    @Schema(description = "Токен для обновления токена доступа, при истечении времени жизни")
    private String refreshToken;
}