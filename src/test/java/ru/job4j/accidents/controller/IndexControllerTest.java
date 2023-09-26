package ru.job4j.accidents.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class IndexControllerTest {

    @InjectMocks
    private IndexController controller;

    @Mock
    private Model model;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(model);
    }

    @Test
    public void whenGetIndex() {
        String actual = controller.getIndex(model);

        assertNotNull(actual);
        assertEquals("index/index", actual);
        verify(model).addAttribute("user", "Konstantin");
        verifyNoMoreInteractions(model);
    }
}