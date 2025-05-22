package ru.gb.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.api.FileEntity;
import ru.gb.dto.FilesResponse;
import ru.gb.service.FileService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

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
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) throws MalformedURLException {
        Resource resource = fileService.loadFileAsResource(id);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping
    public ResponseEntity<List<FilesResponse>> getAllFiles() throws MalformedURLException {

        List<FilesResponse> allFiles = fileService.getAllFileInfo();

        if (allFiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                           .body(allFiles);
    }

}
