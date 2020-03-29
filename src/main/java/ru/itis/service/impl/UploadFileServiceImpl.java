package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.Dto.UserConfirmDto;
import ru.itis.service.UploadFileService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;


@Component
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private Environment environment;

    @Override
    public void saveFile(MultipartFile file) {

        String name = file.getOriginalFilename();
        String allName = environment.getProperty("storage.path") + "/" + name;
        System.out.println(allName);
        try {
            file.transferTo(Paths.get(allName));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public File findFile(String fileName) {
        String path = environment.getProperty("storage.path") + "/" + fileName;
        System.out.println(path);
        return new File(path);
    }

}
