package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.api.CalendarUnit;

public interface CalendarUnitRepository extends JpaRepository<CalendarUnit, Long> {
}
