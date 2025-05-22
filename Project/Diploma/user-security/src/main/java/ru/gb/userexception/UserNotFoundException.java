package ru.gb.userexception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Пользователь не найден");
    }

    public UserNotFoundException(Long message) {
        super("Не найден пользователь c id: " +  message);
    }
}
