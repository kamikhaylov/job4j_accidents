package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import java.util.Optional;
import java.util.Set;

import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC0001;
import static ru.job4j.accidents.common.logging.AccidentLogEvent.ACC0002;

/**
 * Контроллер работы с одним инцидентом
 */
@Controller
@AllArgsConstructor
@RequestMapping("/accident")
public class AccidentController {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    /** Показать страницу добавления инцидента */
    @GetMapping("/create")
    public String viewCreate(Model model) {
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "accident/create";
    }

    /** Добавить инцидент */
    @PostMapping("/create")
    public String create(@ModelAttribute Accident accident,
                         @RequestParam("type.id") int typeId,
                         @RequestParam("ruleIds") Set<Integer> ruleIds) {
        accidentService.create(accident, typeId, ruleIds);
        return "redirect:/accidents/list";
    }

    /** Показать страницу редактирования инцидента */
    @GetMapping("/edit")
    public String viewEdit(Model model, @RequestParam("id") int id) {
        Optional<Accident> accident = accidentService.getById(id);
        if (accident.isEmpty()) {
            model.addAttribute("text", ACC0001.toString());
            return "error/error";
        }
        model.addAttribute("accident", accident.get());
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "accident/edit";
    }

    /** Редактировать инцидент */
    @PostMapping("/edit")
    public String edit(Model model,
                       @ModelAttribute Accident accident,
                       @RequestParam("type.id") int typeId,
                       @RequestParam("ruleIds") Set<Integer> ruleIds) {
        if (!accidentService.update(accident, typeId, ruleIds)) {
            model.addAttribute("text", ACC0002.toString());
            return "error/error";
        }
        return "redirect:/accidents/list";
    }

    /** Показать страницу детальной информации по инциденту */
    @GetMapping("/details")
    public String viewDetails(Model model, @RequestParam("id") int id) {
        Optional<Accident> accident = accidentService.getById(id);
        if (accident.isEmpty()) {
            model.addAttribute("text", ACC0001.toString());
            return "error/error";
        }
        model.addAttribute("accident", accident.get());
        return "accident/details";
    }
}
