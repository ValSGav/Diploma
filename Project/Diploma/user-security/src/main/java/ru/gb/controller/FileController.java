package ru.gb.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.api.FileEntity;
import ru.gb.service.FileService;

import org.springframework.core.io.Resource;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("userId") Long userId) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Проверка размера и типа
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.status( HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }

        FileEntity savedFile = fileService.saveFile(file, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws MalformedURLException {
        Resource resource = fileService.loadFileAsResource(id);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
