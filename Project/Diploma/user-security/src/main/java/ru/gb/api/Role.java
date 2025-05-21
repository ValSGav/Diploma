package ru.gb.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    USER("USER"),
    PHOTOGRAPHER("PHOTOGRAPHER");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }

}