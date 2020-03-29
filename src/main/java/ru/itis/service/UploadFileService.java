package ru.itis.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.Dto.UserConfirmDto;

import java.io.File;

public interface UploadFileService {
    void saveFile(MultipartFile file);

    File findFile(String fileName);
}
