package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.Dto.UserConfirmDto;
import ru.itis.model.User;
import ru.itis.service.UploadFileService;
import ru.itis.service.impl.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FilesController {

    @Autowired
    private Environment environment;


    @Autowired
    UploadFileService fileService;


    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        fileService.saveFile(multipartFile);
        return modelAndView;
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public ModelAndView getFiles() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("files");
        return modelAndView;
    }

    @RequestMapping(value = "/files/file", method = RequestMethod.POST)
    public ModelAndView getFile(@RequestParam("filename") String filename, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        UserConfirmDto user = (UserConfirmDto) session.getAttribute("current_user");
        modelAndView.addObject("user", user);
        filename = environment.getProperty("storage.path") + "/" + filename;
        modelAndView.addObject("file", filename);
        System.out.println(filename);
        return modelAndView;
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/files/{file-name:.+}", method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName, HttpSession session, HttpServletResponse response) {
        File file = fileService.findFile(fileName);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        try {
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        return null;
    }

}
