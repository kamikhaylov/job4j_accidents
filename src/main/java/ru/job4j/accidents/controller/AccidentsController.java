package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.service.AccidentsService;

/**
 * Контроллер начальной страницы
 */
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentsController {

    private final AccidentsService accidentsService;

    /**
     * Получение всех автонарушений
     *
     * @return страница со списком всех автонарушений
     */
    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("accidents", accidentsService.getAll());
        return "accident/list";
    }
}
