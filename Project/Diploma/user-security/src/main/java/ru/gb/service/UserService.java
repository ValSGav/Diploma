package ru.gb.service;


import ru.gb.api.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.repository.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if ( userRepository.findByUsername( user.getUsername() ).isPresent() ) {
            throw new IllegalArgumentException( "Пользователь с таким именем уже существует" );
        }
        if ( userRepository.findByEmail( user.getEmail() ).isPresent() ) {
            throw new IllegalArgumentException( "Пользователь с таким email уже существует" );
        }
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );

        return userRepository.save( user );
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById( id );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById( id )
                .map( user -> {
                    user.setEmail( updatedUser.getEmail() );
                    user.setUsername( updatedUser.getUsername() );
                    if ( updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty() ) {
                        user.setPassword( passwordEncoder.encode( updatedUser.getPassword() ) );
                    }
                    return userRepository.save( user );
                } )
                .orElseThrow( () -> new NoSuchElementException( "Пользователь не найден" ) );
    }

    public void deleteUser(Long id) {
        userRepository.deleteById( id );
    }

}
