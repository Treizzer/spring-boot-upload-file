package com.load.files.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFilesService {

    String handleFileUpload(MultipartFile file) throws Exception;

}
