package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.api.PhotoSession;

public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Long> {
}
