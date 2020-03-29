package ru.itis.service;

import ru.itis.Dto.UserConfirmDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    UserConfirmDto signIn(HttpServletRequest request);
}
