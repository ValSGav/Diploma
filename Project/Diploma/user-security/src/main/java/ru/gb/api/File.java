package ru.gb.api;

import jakarta.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue
    private Long id;

    private String filename;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    // геттеры/сеттеры
}
