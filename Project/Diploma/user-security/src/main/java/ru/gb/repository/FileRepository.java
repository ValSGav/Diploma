package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.api.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
