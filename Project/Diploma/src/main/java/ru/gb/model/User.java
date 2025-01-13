package ru.gb.model;


import lombok.Data;

import java.util.Calendar;

@Data
public abstract class User {
    /**
     * Идентификатор пользователя
     */
    private int id;
    /**
     * Имя пользователя
     */
    private String name;
    /**
     * Дата рождения
     */
    Calendar birthday;

}
