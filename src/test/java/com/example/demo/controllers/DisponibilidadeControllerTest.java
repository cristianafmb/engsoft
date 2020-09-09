package com.example.demo.controllers;

import com.example.demo.controllers.DisponibilidadeController;
import com.example.demo.models.Disponibilidade;
import com.example.demo.repositories.DisponibilidadeRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = DisponibilidadeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DisponibilidadeControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisponibilidadeRepo disponibilidadeRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void init(){
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    public void getAllDisponibilidades() throws Exception {
        Set<Disponibilidade> disponibilidades = new HashSet<>();
        Disponibilidade disponibilidade1 = new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,00));
        Disponibilidade disponibilidade2 = new Disponibilidade(DayOfWeek.THURSDAY, LocalTime.of(9,00), LocalTime.of(12,00));

        disponibilidades.add(disponibilidade1);
        disponibilidades.add(disponibilidade2);
        when(this.disponibilidadeRepo.findAll()).thenReturn(disponibilidades);

        String jsonResult = this.mockMvc.perform(get("/disponibilidade")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()
        )       .andReturn().getResponse().getContentAsString();

        List<Disponibilidade> someDisponibilidadesResult =
                objectMapper.readValue(jsonResult, new TypeReference<>() {
                });

        assertTrue(disponibilidades.containsAll(someDisponibilidadesResult));
    }


}
