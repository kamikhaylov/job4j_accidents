package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер начальной страницы
 */
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class IndexController {

    /**
     * Отображение начальной страницы
     *
     * @return начальная страница
     */
    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index/index";
    }
}
