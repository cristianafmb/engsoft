package com.example.demo.controllers;

import com.example.demo.models.Disponibilidade;
import com.example.demo.repositories.DisponibilidadeRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disponibilidade")
public class DisponibilidadeController {
    private DisponibilidadeRepo disponibilidadeRepo;

    public DisponibilidadeController(DisponibilidadeRepo dispoRepo) {
        this.disponibilidadeRepo = dispoRepo;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Disponibilidade>> getAllDisponibilidades() {

        return ResponseEntity.ok(this.disponibilidadeRepo.findAll());
    }

}
