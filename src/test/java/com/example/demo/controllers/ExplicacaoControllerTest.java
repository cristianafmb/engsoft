package com.example.demo.controllers;

import com.example.demo.models.Explicacao;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicacaoRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ExplicacaoController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExplicacaoControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicacaoRepo explicacaoRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void init(){
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    @Test
    public void getAllExplicadores() throws Exception {
        Set<Explicacao> explicacoes = new HashSet<>();
       Explicacao explicacao1 = new Explicacao(LocalDateTime.of(2020,9,7,9,00)); // segunda
       Explicacao explicador2 = new Explicacao(LocalDateTime.of(2020,9,7,10,00)); // segunda
       explicacoes.add(explicacao1);
       explicacoes.add(explicador2);

        when(this.explicacaoRepo.findAll()).thenReturn(explicacoes);
        String jsonResult = this.mockMvc.perform(get("/explicacao/explicacoes")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()
        )       .andReturn().getResponse().getContentAsString();

        List<Explicacao> someexplicacoesResult =
                objectMapper.readValue(jsonResult, new TypeReference<>() {
                });

        assertTrue(explicacoes.containsAll(someexplicacoesResult));

    }
}
