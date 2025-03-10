package ru.gb.service;

import ru.gb.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.gb.repository.UserRepository;

import java.util.ArrayList;

public class PhotoUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername( username );
        if ( user == null )
            throw new UsernameNotFoundException( String.format( "User with name %s not found", username ) );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername()
                , user.getPassword()
                , new ArrayList<>() );
    }
}
