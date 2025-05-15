package ru.gb.api;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<FileEntity> fileEntities = new ArrayList<>();
}