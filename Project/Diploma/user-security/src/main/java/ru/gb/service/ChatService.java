package ru.gb.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.gb.dto.ChatMessageDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final Path chatStoragePath = Paths.get("chat_messages").toAbsolutePath().normalize();

    public ChatService() throws IOException {
        Files.createDirectories(chatStoragePath);
    }

    public synchronized void saveMessage(ChatMessageDTO message) {
        try {
            Path filePath = chatStoragePath.resolve("chat_" + message.getChatId() + ".json");
            List<ChatMessageDTO> messages;

            if (Files.exists(filePath)) {
                String content = Files.readString(filePath);
                messages = new ObjectMapper().readValue(content,
                        new TypeReference<List<ChatMessageDTO>>(){});
            } else {
                messages = new ArrayList<>();
            }

            messages.add(message);

            String json = new ObjectMapper().writeValueAsString(messages);
            Files.writeString(filePath, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
