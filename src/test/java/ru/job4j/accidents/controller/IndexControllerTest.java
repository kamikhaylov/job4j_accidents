package ru.job4j.accidents.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class IndexControllerTest {

    @InjectMocks
    private IndexController controller;

    @Mock
    private Model model;
    @Mock
    private SecurityContext context;
    @Mock
    private Authentication authentication;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(model, context, authentication);
    }

    @Test
    public void whenGetIndex() {
        try (MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            Object object = new Object();
            securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(context);
            when(context.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(object);

            String actual = controller.getIndex(model);

            assertNotNull(actual);
            assertEquals("index/index", actual);
            verify(model).addAttribute("user", object);
            verifyNoMoreInteractions(model);
        }
    }
}