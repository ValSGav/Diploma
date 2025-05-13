package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.api.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
