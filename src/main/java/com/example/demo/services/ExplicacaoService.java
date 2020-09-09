package com.example.demo.services;

import com.example.demo.controllers.ExplicacaoController;
import com.example.demo.models.Cadeira;
import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicacao;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicacaoRepo;
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
public class ExplicacaoService {
    @Autowired
    public ExplicacaoRepo explicacaoRepo;

    public ExplicadorRepo explicadorRepo;
    Logger logger = LoggerFactory.getLogger(ExplicacaoController.class);
    public ExplicacaoService(ExplicacaoRepo explicacaoRepo) {
        this.explicacaoRepo = explicacaoRepo;
    }

    public List<Explicacao>  getAllExplicacoes(){
        return (List<Explicacao>) explicacaoRepo.findAll();
    };

    public Explicacao createExplicacao(Explicacao explfinal){
        Iterable<Explicacao> explicacoesFromDB = explicacaoRepo.findAll();

        for(Explicacao expl : explicacoesFromDB){
            if(expl.getDia().equals(explfinal.getDia()) && expl.getDia().getHour() == explfinal.getDia().getHour() // SAME DAY, SAME HOUR
                    /**&& expl.getExplicador().getNome().equalsIgnoreCase(explfinal.getExplicador().getNome())*/){ // SAME EXPLICADOR
               logger.error("ERROR!! ATENCION ATENCION!! ERROR CREATING EXPLICACAO!! CAREFULL!");
               return null;
            }
        }

        logger.info("EXPLICACAO: Success!! An Explicacao has been created!");
        explicacaoRepo.save(explfinal);
        return explfinal;
    }
}
