package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

/**
 * Контроллер работы с одним инцидентом
 */
@Controller
@AllArgsConstructor
@RequestMapping("/accident")
public class AccidentController {

    private final AccidentService accidentService;

    /** Показать страницу добавления инцидента */
    @GetMapping("/create")
    public String viewCreate() {
        return "accident/create";
    }

    /** Добавить инцидент */
    @PostMapping("/create")
    public String create(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/accidents/list";
    }

    /** Показать страницу редактирования инцидента */
    @GetMapping("/edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("accident", accidentService.getById(id));
        return "accident/edit";
    }

    /** Редактировать инцидент */
    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/accidents/list";
    }

    /** Показать страницу детальной информации по инциденту */
    @GetMapping("/details/{id}")
    public String viewDetails(Model model, @PathVariable("id") int id) {
        model.addAttribute("accident", accidentService.getById(id));
        return "accident/details";
    }
}
