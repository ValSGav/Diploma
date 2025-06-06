package ru.gb.api;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class CalendarUnit {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @Column(name = "hour_column")
    private Integer hour; // 0-23

    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;

    @OneToOne(mappedBy = "calendarUnit")
    private User client;
}