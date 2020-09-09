package com.example.demo.models;

import com.example.demo.controllers.ExplicacaoController;
import com.example.demo.controllers.ExplicadorController;
import com.example.demo.repositories.DisponibilidadeRepo;
import com.example.demo.repositories.ExplicadorRepo;
import com.example.demo.services.ExplicadorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.apache.tomcat.jni.Local;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.zip.Adler32;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExplicadorTest {

    @Autowired
    private ExplicadorRepo explicadorRepo;

    @Autowired
    private DisponibilidadeRepo disponibilidadeRepo;


    @Test
    public void addDisponibilidade(){
        Explicador explicador = new Explicador("Sandro", "sandro1234@gmail.com");

        Disponibilidade disponibilidade1 = new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,00));
        Disponibilidade disponibilidade2 = new Disponibilidade(DayOfWeek.THURSDAY, LocalTime.of(9,00), LocalTime.of(12,00));

        explicador.addDisponibilidade(disponibilidade1);
        explicador.addDisponibilidade(disponibilidade2);

        assertEquals(2, explicador.getDisponibilidades().size()); // RIGHT

        disponibilidade1.setExplicador(explicador);
        assertEquals("Sandro", disponibilidade1.getExplicador().nome);
    }

    @Test
    public void containsDisponibilidade() {
        Explicador explicador = new Explicador("Sandro", "sandro1234@gmail.com");

        Disponibilidade disponibilidade1 = new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,00));
        Disponibilidade disponibilidade2 = new Disponibilidade(DayOfWeek.THURSDAY, LocalTime.of(9,00), LocalTime.of(12,00));

        explicador.addDisponibilidade(disponibilidade1);
        explicador.addDisponibilidade(disponibilidade2);

        Disponibilidade disponibilidadePesquisa = new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(8,00), LocalTime.of(13,00));
        Disponibilidade disponibilidadeErrada = new Disponibilidade(DayOfWeek.THURSDAY, LocalTime.of(12,00), LocalTime.of(18,00));
        Disponibilidade disponibilidadePesquisa2 = new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,00));
        assertTrue(explicador.containsDisponibilidade(disponibilidadePesquisa)); // RIGHT
        assertTrue(explicador.containsDisponibilidade(disponibilidadePesquisa2)); // RIGHT
        assertEquals(false, explicador.containsDisponibilidade(disponibilidadeErrada)); // WRONG
    }

    @Test
    public void addExplicacao() {
        Explicador mimi = new Explicador("Mimi", "mimi1234@gmail.com");
        Aluno fernando = new Aluno("Fernando");
        Explicacao explicacao1 = new Explicacao(LocalDateTime.of(2020,9,7,10,00)); // segunda feira
        fernando.setExplicador(mimi);
        mimi.alunos.add(fernando);
        mimi.disponibilidades.add(new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,00)));
        mimi.addExplicacao(explicacao1, fernando);

        assertEquals(1, mimi.getDisponibilidades().size());
        assertEquals("Mimi", explicacao1.getExplicador().getNome());
    }
}