package ru.gb.service;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.api.FileEntity;
import ru.gb.api.User;
import ru.gb.repository.FileRepository;
import ru.gb.repository.UserRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    public FileService(FileRepository fileRepository, UserRepository userRepository) throws IOException {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        Files.createDirectories(fileStorageLocation);
    }

    public FileEntity saveFile(MultipartFile file, Long userId) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = fileStorageLocation.resolve(filename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(filename);
        fileEntity.setFilepath(targetLocation.toString());
        fileEntity.setOwner(user);

        return fileRepository.save(fileEntity);
    }

    public Resource loadFileAsResource(Long fileId) throws MalformedURLException {
        FileEntity fileEntity = fileRepository.findById(fileId).orElse(null);
        if (fileEntity == null) return null;

        Path filePath = Paths.get(fileEntity.getFilepath());
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;
        }
        return null;
    }
}
