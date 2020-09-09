package com.example.demo.controllers;

import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicadorRepo;
import com.example.demo.services.ExplicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Controller
@RequestMapping(value = "/explicador")
public class ExplicadorController {
    /*@Autowired
    private ExplicadorRepo explicadorRepo;*/

    private ExplicadorService explicadorService;

    public ExplicadorController(ExplicadorService explicadorService){
        this.explicadorService = explicadorService;
    }

    @RequestMapping(value = "/explicadores", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Explicador>> getAllExplicadores(){
        return ResponseEntity.ok(this.explicadorService.getAllExplicadores());
    }


    @RequestMapping(value = "/{searchEmail}", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> getExplicadorByEmail(@PathVariable(value = "searchEmail") String searchEmail) {
        return ResponseEntity.ok(this.explicadorService.findByEmail(searchEmail));
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
   // @GetMapping("explicador?cadeira={cadeira}&dia={dia}&inicio={hora_inicio}&fim=hora_fim}")

    public ResponseEntity<Iterable<Explicador>> filterSearch (@RequestParam String cadeira,
                                                              @RequestParam String dia,
                                                              @RequestParam("inicio") Integer horaInicio,
                                                              @RequestParam("fim") Integer horaFim){
        return ResponseEntity.ok(this.explicadorService.findExplicador(cadeira, dia, horaInicio, horaFim));
    }
    @RequestMapping("/create")
    @ResponseBody
    public Explicador save(@RequestBody Explicador explicador) {
        return explicadorService.createExplicador(explicador);
    }
}
