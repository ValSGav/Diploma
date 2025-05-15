package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.api.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
