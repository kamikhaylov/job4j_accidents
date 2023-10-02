package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Application;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.utils.RecordCreator;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class AccidentControllerItTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentService accidentService;

    @Test
    @WithMockUser
    public void whenViewCreate() throws Exception {
        this.mockMvc.perform(get("/accident/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/create"));
    }

    @Test
    @WithMockUser
    public void whenCreate() throws Exception {
        AccidentType type = RecordCreator.createType();
        Set<Rule> rules = RecordCreator.createRules();
        Accident accident = RecordCreator.createAccident(type, rules);

        this.mockMvc.perform(post("/accident/create")
                .param("id", String.valueOf(accident.getId()))
                        .param("name", accident.getName())
                        .param("address", accident.getAddress())
                        .param("type.id", String.valueOf(accident.getType().getId()))
                        .param("text", accident.getText())
                        .param("ruleIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/accidents/list"));
        verify(accidentService).create(accident, type.getId(), Set.of(1));
    }

    @Test
    @WithMockUser
    public void whenViewEdit() throws Exception {
        AccidentType type = RecordCreator.createType();
        Set<Rule> rules = RecordCreator.createRules();
        Accident accident = RecordCreator.createAccident(type, rules);

        when(accidentService.getById(accident.getId())).thenReturn(Optional.of(accident));

        this.mockMvc.perform(get("/accident/edit")
                .param("id", String.valueOf(accident.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/edit"));
    }

    @Test
    @WithMockUser
    public void whenEdit() throws Exception {
        AccidentType type = RecordCreator.createType();
        Set<Rule> rules = RecordCreator.createRules();
        Accident accident = RecordCreator.createAccident(type, rules);

        when(accidentService.update(accident, type.getId(), Set.of(1))).thenReturn(true);

        this.mockMvc.perform(post("/accident/edit")
                .param("id", String.valueOf(accident.getId()))
                .param("name", accident.getName())
                .param("address", accident.getAddress())
                .param("type.id", String.valueOf(accident.getType().getId()))
                .param("text", accident.getText())
                .param("ruleIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/accidents/list"));
        verify(accidentService).update(accident, type.getId(), Set.of(1));
    }

    @Test
    @WithMockUser
    public void whenViewDetails() throws Exception {
        AccidentType type = RecordCreator.createType();
        Set<Rule> rules = RecordCreator.createRules();
        Accident accident = RecordCreator.createAccident(type, rules);

        when(accidentService.getById(accident.getId())).thenReturn(Optional.of(accident));

        this.mockMvc.perform(get("/accident/details")
                .param("id", String.valueOf(accident.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/details"));
    }
}