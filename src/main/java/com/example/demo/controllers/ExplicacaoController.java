package com.example.demo.controllers;

import com.example.demo.models.Aluno;
import com.example.demo.models.Explicacao;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicacaoRepo;
import com.example.demo.services.ExplicacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "/explicacao")
public class ExplicacaoController {

    public ExplicacaoService explicacaoService;

    public ExplicacaoController(ExplicacaoService explicacaoService){
        this.explicacaoService = explicacaoService;
    }

    @RequestMapping(value = "/explicacoes", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Explicacao>> getAllExplicacoes(){
        return ResponseEntity.ok(this.explicacaoService.getAllExplicacoes());
    }

    @RequestMapping("/create")
    @ResponseBody
    public Explicacao createExplicacao(@RequestBody Explicacao explicacao, Explicador explicador, Aluno aluno) {
        explicacao.setExplicador(explicador);
        explicacao.setAluno(aluno);
        return explicacaoService.createExplicacao(explicacao);
    }
}
