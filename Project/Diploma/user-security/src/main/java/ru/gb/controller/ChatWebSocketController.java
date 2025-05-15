package ru.gb.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.gb.dto.ChatMessageDTO;
import ru.gb.service.ChatService;

@Controller
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        // Добавим валидацию
        if (chatMessageDTO.getContent() == null || chatMessageDTO.getContent().trim().isEmpty())
            return;

        // Сохраняем сообщение локально
        chatService.saveMessage(chatMessageDTO);

        // Отправляем всем подписанным клиентам выбранного чата
        messagingTemplate.convertAndSend("/topic/chat." + chatMessageDTO.getChatId(), chatMessageDTO);
    }
}

