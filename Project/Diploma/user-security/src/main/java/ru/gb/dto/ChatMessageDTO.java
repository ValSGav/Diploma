package ru.gb.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private Long chatId;
    private Long senderId;
    private String content;
}
