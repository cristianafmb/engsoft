package com.example.demo.controllers;

import com.example.demo.models.Cadeira;
import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicadorRepo;
import com.example.demo.services.ExplicadorService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ExplicadorController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExplicadorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicadorService explicadorRepo;


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void init(){
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    @Test
    public void getExplicadorByEmail() throws Exception {
        Set<Explicador> explicadores = new HashSet<>();
        Explicador explicador1 = new Explicador("Luis", "luis1234@gmail.com");
        Explicador explicador2 = new Explicador("Mila", "mila1234@gmail.com");

        explicadores.add(explicador1);
        explicadores.add(explicador2);
        String searchEmail = "mila1234@gmail.com";
        Explicador explicadorResult = new Explicador();

        when(this.explicadorRepo.findByEmail(searchEmail)).thenReturn(explicador2);
        String jsonResult = this.mockMvc.perform(get("/explicador/"+searchEmail)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()
        )       .andReturn().getResponse().getContentAsString();

        explicadorResult =  objectMapper.readValue(jsonResult, new TypeReference<>() {});
        assertTrue(explicadorResult.email.equals(searchEmail));


    }
    @Test
    public void getAllExplicadores() throws Exception {
        List<Explicador> explicadores = new ArrayList<>();
        Explicador explicador1 = new Explicador("Luis", "luis1234@gmail.com");
        Explicador explicador2 = new Explicador("Mila", "mila1234@gmail.com");

        explicadores.add(explicador1);
        explicadores.add(explicador2);

        when(this.explicadorRepo.getAllExplicadores()).thenReturn(explicadores);

        String jsonResult = this.mockMvc.perform(get("/explicador/explicadores")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()
        )       .andReturn().getResponse().getContentAsString();

        List<Explicador> someexplicadoresResult =
                objectMapper.readValue(jsonResult, new TypeReference<>() {
                });

        assertTrue(explicadores.containsAll(someexplicadoresResult));

    }
/**
    @Test
    public void save() throws Exception{
        Explicador rosa = new Explicador("Rosa", "rosa1234@gmail.com");
        when(this.explicadorRepo.save(rosa)).thenReturn(rosa);
        ResultActions jsonResult = this.mockMvc.perform(post("/explicador/create")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rosa))).andExpect(status().isCreated())
                .andExpect(status().isOk());

        Explicador result = objectMapper.readValue((JsonParser) jsonResult, new TypeReference<>() {});

        assertTrue(result.nome.equals(rosa.nome));
    }
**/

}
