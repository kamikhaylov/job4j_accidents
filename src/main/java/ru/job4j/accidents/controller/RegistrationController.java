package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.UserService;

import java.util.Optional;

import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC0005;

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
    public String regSave(Model model, @ModelAttribute User user) {
        Optional<User> result = userService.create(user);
        if (result.isEmpty()) {
            model.addAttribute("errorMessage",
                    ACC0005.getTitle() + " " + user.getUsername());
            model.addAttribute("user", null);
            return "user/registration";
        }
        return "redirect:/user/login";
    }
}
