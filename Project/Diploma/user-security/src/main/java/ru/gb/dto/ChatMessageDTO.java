package ru.gb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Сообщение чата")
public class ChatMessageDTO {
    @Schema(description = "Идентификатор чата")
    private Long chatId;
    @Schema(description = "Идентификатор отправителя")
    private Long senderId;
    @Schema(description = "Содержимое сообщения")
    private String content;
}
