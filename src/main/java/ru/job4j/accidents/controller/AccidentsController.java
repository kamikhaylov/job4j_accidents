package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.service.AccidentsService;

/**
 * Контроллер работы со списками инцидентов
 */
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentsController {

    private final AccidentsService accidentsService;

    /** Страница со всеми инцидентами */
    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentsService.getAll());
        return "accident/list";
    }
}
