package ru.gb.api;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PhotoSession {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User photographer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @OneToMany(mappedBy = "photoSession", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "photoSession", cascade = CascadeType.ALL)
    private List<FileEntity> fileEntities = new ArrayList<>();


}
