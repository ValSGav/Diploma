package ru.gb.model;

import lombok.Data;

@Data
public class Comment {
    private User Photographer;
    private User Client;
    private String Comment;
}
