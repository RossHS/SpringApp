package com.khapilov.currency.converter.controllers;

import com.khapilov.currency.converter.domain.Role;
import com.khapilov.currency.converter.domain.User;
import com.khapilov.currency.converter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

/**
 * @author Ross Khapilov
 * @version 1.0 01.08.2020
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("message", "Введите логин и пароль");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "Пользователь с таким именем уже зарегистрирован");
            return "registration";
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            model.addAttribute("message", "Пароль не может быть пустым");
            return "registration";
        }
        model.addAttribute("message", "Введите логин и пароль");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
