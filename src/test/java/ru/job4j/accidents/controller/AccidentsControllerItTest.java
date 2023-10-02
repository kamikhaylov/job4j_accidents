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
import ru.job4j.accidents.service.AccidentsService;
import ru.job4j.accidents.utils.RecordCreator;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class AccidentsControllerItTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentsService accidentsService;

    @Test
    @WithMockUser
    public void whenGetAll() throws Exception {

        AccidentType type = RecordCreator.createType();
        Set<Rule> rules = RecordCreator.createRules();
        Accident accident = RecordCreator.createAccident(type, rules);

        when(accidentsService.getAll()).thenReturn(List.of(accident));

        this.mockMvc.perform(get("/accidents/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/list"));
    }
}