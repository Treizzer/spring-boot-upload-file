package com.load.files.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.load.files.services.UploadFilesService;

@RestController
@RequestMapping("/upload")
public class UploadFilesController {

    @Autowired
    UploadFilesService service;

    @PostMapping("/files")
    private ResponseEntity<String> uploadPicture(@RequestParam("file") MultipartFile file)
            throws Exception {
        return new ResponseEntity<>(service.handleFileUpload(file), HttpStatus.OK);
    }

}
