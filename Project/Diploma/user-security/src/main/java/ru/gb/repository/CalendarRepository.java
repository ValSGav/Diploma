package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.api.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
