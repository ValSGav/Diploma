package ru.gb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ответ на запрос. Данные о файле")
public class FilesResponse {

    @Schema(description = "Имя файла")
    private String filename;

    @Schema(description = "полный путь до файла на диске")
    private String filepath;

    @Schema(description = "web ссылка на файл на диске")
    private String url;

    @Schema(description = "Владелец файла")
    private String owner;

}
