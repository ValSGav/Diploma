package ru.gb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.Chat;
import ru.gb.api.Message;
import ru.gb.service.ChatService;
import ru.gb.userexception.ChatNotFoundException;
import ru.gb.userexception.UserNotFoundException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // Создание чата
    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Set<Long> userIds) throws UserNotFoundException {
        Chat chat = chatService.createChat(userIds);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/api/chats/" + chat.getId())
                .body(chat);
    }

    // Добавление пользователя в чат
    @PutMapping("/{chatId}/users/{userId}")
    public ResponseEntity<Void> addUserToChat(
            @PathVariable("chatId") Long chatId,
            @PathVariable("userId") Long userId) throws UserNotFoundException, ChatNotFoundException {
        chatService.addUserToChat(chatId, userId);
        return ResponseEntity.noContent().build();
    }

    // Отправка сообщения
    @PostMapping("/{chatId}/messages")
    public ResponseEntity<Message> sendMessage(
            @PathVariable Long chatId,
            @RequestParam Long senderId,
            @RequestParam String content) throws UserNotFoundException, ChatNotFoundException {
        Message message = chatService.sendMessage(chatId, senderId, content);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/api/chats/" + chatId + "/messages/" + message.getId())
                .body(message);
    }

    // Получение всех сообщений чата
    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable Long chatId) throws ChatNotFoundException {
        List<Message> messages = chatService.getAllMessages(chatId);
        return ResponseEntity.ok(messages);
    }

    // Получение последних N сообщений
    @GetMapping("/{chatId}/messages/latest")
    public ResponseEntity<List<Message>> getLatestMessages(
            @PathVariable Long chatId,
            @RequestParam(defaultValue = "10") int limit) throws ChatNotFoundException {
        List<Message> latestMessages = chatService.getLatestMessages(chatId, limit);
        return ResponseEntity.ok(latestMessages);
    }

}

