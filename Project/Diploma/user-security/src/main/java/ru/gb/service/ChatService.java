package ru.gb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.api.Chat;
import ru.gb.api.Message;
import ru.gb.api.User;
import ru.gb.repository.ChatRepository;
import ru.gb.repository.MessageRepository;
import ru.gb.repository.UserRepository;
import ru.gb.userexception.ChatNotFoundException;
import ru.gb.userexception.UserNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;



    public Chat createChat(Set<Long> userIds) throws UserNotFoundException{
        Chat chat = new Chat();
        userIds.forEach(userId -> {
            User user = null;
            try {
                user = userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId));
            } catch (UserNotFoundException e) {
                throw new RuntimeException( e );
            }

            chat.getParticipants().add(user);
        });
        return chatRepository.save(chat);
    }

    public void addUserToChat(Long chatId, Long userId) throws ChatNotFoundException, UserNotFoundException {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        chat.getParticipants().add(user);
        chatRepository.save(chat);
    }

    public Message sendMessage(Long chatId, Long senderId, String content) throws ChatNotFoundException, UserNotFoundException {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException(senderId));
        Message message = new Message(sender, chat, content);
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(Long chatId) throws ChatNotFoundException {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));
        return chat.getMessages();
    }

    public List<Message> getLatestMessages(Long chatId, int limit) throws ChatNotFoundException {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));

        return chat.getMessages()
                .stream()
                .sorted( Comparator.comparing(Message::getSentAt).reversed())
                .limit(limit)
                .collect( Collectors.toList());
    }
}
