package ru.gb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "Запрос. Обновление токена доступа")
public class RefreshJwtRequest {
    @Schema(description = "Токен для обновления токена доступа")
    public String refreshToken;
}
