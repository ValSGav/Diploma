package ru.gb.api;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Calendar {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<CalendarUnit> units = new ArrayList<>();
}
