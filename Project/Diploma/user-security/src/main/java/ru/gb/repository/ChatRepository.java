package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.api.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
