package ru.itis.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.Dto.UserConfirmDto;
import ru.itis.service.impl.UserDetailsImpl;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView view(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user.getUser());
        modelAndView.setViewName("profile");
        return modelAndView;
    }
}
