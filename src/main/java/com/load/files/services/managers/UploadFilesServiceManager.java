package com.load.files.services.managers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.load.files.services.UploadFilesService;

@Service
public class UploadFilesServiceManager implements UploadFilesService {

    private final long MAX_FILE_SIZE = (5 * 1024 * 1024);

    @Override
    public String handleFileUpload(MultipartFile file) throws Exception {
        try {
            String fileOriginalName = file.getOriginalFilename();
            if (fileOriginalName == null) {
                return "Was impossible upload the file / Fue imposible subir el archivo";
            }

            String fileName = UUID.randomUUID().toString();
            byte[] fileBytes = file.getBytes();
            long fileSize = file.getSize();

            if (fileSize > MAX_FILE_SIZE) {
                return "File size must be less than or equal 5MB / " +
                        "El archivo debe ser igual o menor a 5MB";
            }

            if (!fileOriginalName.endsWith(".jpg") &&
                    !fileOriginalName.endsWith(".jpeg") &&
                    !fileOriginalName.endsWith(".png")) {
                return "Only JPG, JPEG and PNG files allowed! / " +
                        "Solo se permiten archivos JPG, JPEG y PNG";
            }

            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;
            File folder = new File("src/main/resources/img");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            Path path = Paths.get("src/main/resources/img/" + newFileName);
            Files.write(path, fileBytes);

            return "File has uploaded successfully! / ¡Archivo subido con éxito!";

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
