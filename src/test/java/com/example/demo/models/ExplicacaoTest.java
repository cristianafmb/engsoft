package com.example.demo.models;


import com.example.demo.repositories.ExplicacaoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ExplicacaoTest {
    @Autowired
    private ExplicacaoRepo explicacaoRepo;

    @Test
    public void Explicação(){
        Cadeira cadeira = new Cadeira("Engenharia de Software");
        Explicacao expli = new Explicacao(LocalDateTime.of(2020,9,9,12,00)); // quarta feira
        Aluno aluno = new Aluno("Susana");

        expli.setAluno(aluno);
        aluno.getExplicacoes().add(expli);
        assertEquals(1, aluno.getExplicacoes().size());

        Explicador explicador = new Explicador("David", "davidferreira@gmail.com");
        expli.setExplicador(explicador);
        assertEquals("David",expli.getExplicador().nome);

        expli.setAluno(aluno);
        assertEquals("Susana", expli.getAluno().getNome());

        expli.setCadeira(cadeira);
        assertEquals("Engenharia de Software", expli.getCadeira().getNome());



    }


}
