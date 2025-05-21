package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.api.User;
import ru.gb.repository.UserRepository;

import java.util.stream.Collectors;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByLogin( username )
                .orElseThrow( () -> new UsernameNotFoundException( username ) );

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRoles().stream()
                        .map( role -> new SimpleGrantedAuthority( role.toString() ))
                        .collect( Collectors.toList() )
        );
    }
}
