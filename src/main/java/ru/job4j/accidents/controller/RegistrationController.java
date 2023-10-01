package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.UserService;

/**
 * Контроллер регистрации пользователей
 */
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class RegistrationController {

    private final UserService userService;

    /** Страница регистрации пользователя */
    @GetMapping("/registration")
    public String regPage() {
        return "user/registration";
    }

    /** Создание нового пользователя */
    @PostMapping("/registration")
    public String regSave(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/user/login";
    }
}
