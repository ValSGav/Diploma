package ru.gb.api;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class FileEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String filename;

    private String filepath; // локальный путь на диске

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    private PhotoSession photoSession;

    @OneToMany(mappedBy = "fileEntity", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
