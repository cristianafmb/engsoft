
package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Transactional
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private DisponibilidadeRepo dispoRepo;
    private ExplicadorRepo explicadorRepo;
    private ExplicacaoRepo explicacaoRepo;




    public Bootstrap(DisponibilidadeRepo disponibilidadeRepo, ExplicacaoRepo explicacaoRepo, ExplicadorRepo explicadorRepo) {
        this.dispoRepo = disponibilidadeRepo;
        this.explicacaoRepo = explicacaoRepo;
        this.explicadorRepo = explicadorRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Disponibilidade manhaSegunda = new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,00));
        Disponibilidade tardeSegunda = new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(14,00), LocalTime.of(19,00));
        Disponibilidade manhaTerca = new Disponibilidade(DayOfWeek.TUESDAY, LocalTime.of(9,00), LocalTime.of(12,00));
        Disponibilidade tardeTerca = new Disponibilidade(DayOfWeek.TUESDAY, LocalTime.of(14,00), LocalTime.of(19,00));
        Disponibilidade manhaSexta = new Disponibilidade(DayOfWeek.FRIDAY, LocalTime.of(9,00), LocalTime.of(12,00));
        Disponibilidade tardeSexta = new Disponibilidade(DayOfWeek.FRIDAY, LocalTime.of(14,00), LocalTime.of(19,00));

        this.dispoRepo.save(manhaSegunda);
        this.dispoRepo.save(tardeSegunda);
        this.dispoRepo.save(manhaTerca);
        this.dispoRepo.save(tardeTerca);
        this.dispoRepo.save(manhaSexta);
        this.dispoRepo.save(tardeSexta);

        Explicador gustavo = new Explicador("Gustavo", "gustavo1234@gmail.com");
        Explicador ermelinda = new Explicador("Ermelinda", "ermelinda1234@gmail.com");
        Explicador leticia = new Explicador("Letícia", "leticia1234@gmail.com");

        gustavo.addDisponibilidade(manhaSegunda);
        gustavo.addDisponibilidade(tardeSexta);
        ermelinda.addDisponibilidade(tardeSegunda);
        ermelinda.addDisponibilidade(manhaSexta);
        leticia.addDisponibilidade(manhaTerca);
        leticia.addDisponibilidade(tardeTerca);

        this.explicadorRepo.save(gustavo);
        this.explicadorRepo.save(ermelinda);
        this.explicadorRepo.save(leticia);

        Explicacao explicacao1 = new Explicacao(LocalDateTime.of(2020,9,7,10,00)); //segunda feira
        Explicacao explicacao2 = new Explicacao(LocalDateTime.of(2020,9,7,17,00)); // segunda feira
        explicacao1.setCadeira(new Cadeira("Pintura"));
        explicacao1.setAluno(new Aluno("Francisco"));
        explicacao1.setExplicador(gustavo);

        explicacao2.setCadeira(new Cadeira("Arte"));
        explicacao2.setExplicador(leticia);
        explicacao2.setAluno(new Aluno("Dalila"));

        this.explicacaoRepo.save(explicacao1);
        this.explicacaoRepo.save(explicacao2);

        Cadeira sabre = new Cadeira("Sabre");
        gustavo.addCadeira(sabre);
        this.explicadorRepo.save(gustavo);

    }
}
