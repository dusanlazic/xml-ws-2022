package com.zavod.controller;

import com.zavod.dto.FileUploadResponse;
import com.zavod.service.PrilogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/dokumenti")
public class PrilogController {

    @Autowired
    private PrilogService prilogService;

    @PostMapping(path = "", produces = MediaType.APPLICATION_XML_VALUE)
    public FileUploadResponse upload(@RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
        return prilogService.store(file, authentication);
    }

    @GetMapping(path = "/{dirname}/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@PathVariable String dirname, @PathVariable String filename) throws IOException {
        return prilogService.serve(dirname, filename);
    }

}
