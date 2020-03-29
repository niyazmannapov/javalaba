package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.Dto.UserConfirmDto;
import ru.itis.model.State;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.service.SignUpService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    public UserConfirmDto signUp(HttpServletRequest req) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userRepository.save( User.builder().email(req.getParameter("email"))
                .password(encoder.encode(req.getParameter("password")))
                .login(req.getParameter("login"))
                .confirmCode(UUID.randomUUID().toString())
                .state(State.NOT_CONFIRMED)
                .build());
        return UserConfirmDto.from(userRepository.findByLogin(req.getParameter("login")).get());
    }
}
