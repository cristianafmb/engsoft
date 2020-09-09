package com.example.demo.services;

import com.example.demo.controllers.ExplicacaoController;
import com.example.demo.models.Cadeira;
import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicadorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExplicadorService{
    @Autowired
    public ExplicadorRepo explicadorRepo;

    public ExplicadorService(ExplicadorRepo explicadorRepo) {
        this.explicadorRepo = explicadorRepo;
    }

    Logger logger = LoggerFactory.getLogger(ExplicacaoController.class);
    public List<Explicador>  getAllExplicadores(){
        return (List<Explicador>) explicadorRepo.findAll();
    };

    public List<Explicador> findExplicador(String cadeira, String diaI, Integer inicio, Integer fim){/*LocalTime horaInicio, LocalTime horaFim, DayOfWeek dia */
        String diafinal = diaI.toUpperCase();
        DayOfWeek dia = DayOfWeek.valueOf(diafinal);
        LocalTime horaInicio = LocalTime.of(inicio, 00);
        LocalTime horaFim = LocalTime.of(fim, 00);
        Disponibilidade disponibilidade = new Disponibilidade(dia, horaInicio, horaFim);

        Iterable<Explicador> explicadoresFromDB=explicadorRepo.findAll();
        List<Explicador> resultExplicadores=new ArrayList<>();

        for(Explicador explicador:explicadoresFromDB){
            if(explicador.containsDisponibilidade(disponibilidade)) {
                for (Cadeira cadeira1 : explicador.getCadeiras()) {
                    if (cadeira1.getNome().equalsIgnoreCase(cadeira)) {
                        resultExplicadores.add(explicador);
                    }
                }
            }
        }
        return resultExplicadores;
    }

    public Explicador findByEmail (String searchEmail){
        Iterable<Explicador> explicadoresFromDB = explicadorRepo.findAll();
        for(Explicador expl : explicadoresFromDB){
            if(expl.email.equals(searchEmail)){
                return expl;
            }
        }
        return null;
    }

    public Explicador createExplicador(Explicador explicador){
        Iterable<Explicador> explicadoresFromDB = explicadorRepo.findAll();
        for(Explicador expl : explicadoresFromDB){
            if(expl.email.equals(explicador.email)){
                logger.error("ERROR!! OH NO! SOMETHING WENT WRONG, COULDN'T CREATE EXPLICADOR, EMAIL ALREADY TAKEN");
                return null;
            }
        }
        logger.info("EXPLICADOR: Success!! An Explicador has been created!");
        explicadorRepo.save(explicador);
        return explicador;
    }

}
