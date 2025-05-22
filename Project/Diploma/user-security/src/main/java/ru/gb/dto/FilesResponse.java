package ru.gb.dto;

import lombok.Data;

@Data
public class FilesResponse {

    private String filename;

    private String filepath; // локальный путь на диске

    private String url;

    private String owner;

}
