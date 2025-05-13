package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.api.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
