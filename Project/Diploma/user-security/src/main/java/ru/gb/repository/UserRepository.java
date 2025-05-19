package ru.gb.repository;

import org.springframework.stereotype.Repository;
import ru.gb.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
}
