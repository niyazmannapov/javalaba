package ru.itis.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.Dto.UserConfirmDto;
import ru.itis.service.LoginService;
import ru.itis.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService service;

    @Override
    public UserConfirmDto signIn(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (service.checkPassword(login, password).isPresent())
            return UserConfirmDto.from(service.checkPassword(login, password).get());
        else return null;
    }
}
