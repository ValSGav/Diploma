package ru.gb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "photographs")
public class Photograph {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, updatable = false)
    private Long photographerId;
    @Column(nullable = false, updatable = false)
    private LocalDate uploadDate;
    private String extension;
    private String downloadLink;
}

