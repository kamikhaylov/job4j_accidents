package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.nonNull;
import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC0003;
import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC0004;

/**
 * Контроллер работы с пользователями
 */
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class LoginController {

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        String errorMessage = null;
        if (nonNull(error)) {
            errorMessage = ACC0003.getTitle();
        }
        if (nonNull(logout)) {
            errorMessage = ACC0004.getTitle();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "user/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (nonNull(auth)) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login?logout=true";
    }
}
