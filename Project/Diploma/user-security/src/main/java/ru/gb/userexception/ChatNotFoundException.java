package ru.gb.userexception;

public class ChatNotFoundException extends Exception {
    public ChatNotFoundException(Long chatId) {
        super("Не найден чат c id: " +  chatId);
    }
}
