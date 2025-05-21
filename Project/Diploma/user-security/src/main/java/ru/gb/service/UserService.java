package ru.gb.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.api.Role;
import ru.gb.api.User;
import ru.gb.dto.UserRegistrationRequest;
import ru.gb.repository.UserRepository;
import ru.gb.userexception.UsernameExistsException;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User createUser(User user) throws RoleNotFoundException {
        if ( userRepository.findByUsername( user.getUsername() ).isPresent() ) {
            throw new IllegalArgumentException( "Пользователь с таким именем уже существует" );
        }
        if ( userRepository.findByEmail( user.getEmail() ).isPresent() ) {
            throw new IllegalArgumentException( "Пользователь с таким email уже существует" );
        }
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        HashSet<Role> rolesUser = new HashSet<>();
        rolesUser.add( Role.USER);
        user.setRoles( rolesUser);

        return userRepository.save( user );
    }

    public User registerUser(UserRegistrationRequest registrationDto) throws RoleNotFoundException {
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new UsernameExistsException("Username '" + registrationDto.getUsername() + "' already exists");
        }

        User user = new User();
        user.setUsername( registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.getRoles().add(Role.USER);

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById( id );
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByUsername( name );
    }

    public Optional<User> getByLogin(String name){ return userRepository.findByUsername( name );}

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById( id )
                .map( user -> {
                    user.setEmail( updatedUser.getEmail() );
                    user.setUsername( updatedUser.getUsername());
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
