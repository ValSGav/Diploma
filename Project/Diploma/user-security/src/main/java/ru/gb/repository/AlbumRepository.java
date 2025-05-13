package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.api.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
