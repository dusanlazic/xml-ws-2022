package com.zavod.service;

import com.zavod.dto.FileUploadResponse;
import com.zavod.dto.KorisnikDTO;
import com.zavod.exception.FileUploadException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PrilogService {

    private final Path priloziLocation = Paths.get("prilozi");
    private final String salt = "NaCl";

    public FileUploadResponse store(MultipartFile file, Authentication authentication) throws IOException {
        KorisnikDTO korisnikDTO = (KorisnikDTO) authentication.getPrincipal();
        String userUploadDirName = DigestUtils.md5Hex(salt + korisnikDTO.getEmail());

        Path userUploadDir = priloziLocation.resolve(userUploadDirName);
        Files.createDirectories(userUploadDir);

        String fileName = file.getOriginalFilename();
        Path storedFilePath = userUploadDir.resolve(fileName);

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, storedFilePath, StandardCopyOption.REPLACE_EXISTING);
            return new FileUploadResponse("http://localhost:8082/dokumenti/" + userUploadDirName + "/" + fileName);
        } catch (IOException e) {
            throw new FileUploadException("Failed to store the file.", e);
        }
    }

    public ResponseEntity<Resource> serve(String dirname, String filename) throws IOException {
        Path storedFilePath = priloziLocation.resolve(dirname).resolve(filename);

        Resource resource = new UrlResource(storedFilePath.toUri());
        if (!resource.exists() || !resource.isReadable())
            throw new FileNotFoundException("File not found.");

        return ResponseEntity.ok().body(resource);
    }
}
