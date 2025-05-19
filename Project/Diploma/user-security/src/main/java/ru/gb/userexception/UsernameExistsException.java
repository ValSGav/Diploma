package ru.gb.userexception;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException(String message) {
        super( message );
    }
}

