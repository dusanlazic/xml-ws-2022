package com.zavod.dto;

import lombok.Getter;

@Getter
public class FileUploadResponse {
    private final String fileName;

    public FileUploadResponse(String fileName) {
        this.fileName = fileName;
    }
}