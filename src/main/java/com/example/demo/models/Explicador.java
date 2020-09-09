package com.example.demo.models;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.html.HTMLDocument;

@Data
@Entity(name = "explicador")
public class Explicador extends BaseModel{

    public String nome;

    public String email;

    public Explicador(Explicador explicador) {
        super();
    }
    public Explicador(){}
    public Explicador(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    @OneToMany(cascade=CascadeType.ALL)
    public Set<Cadeira> cadeiras = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL)
    public Set<Disponibilidade> disponibilidades = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL)
    private Set<Explicacao> explicacoes = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL)
    public Set<Aluno> alunos = new HashSet<>();

    public void addCadeira(Cadeira cad){
        for(Cadeira cadeira:this.getCadeiras()){
            if(cad.getNome().equalsIgnoreCase(cad.getNome())){
                return;
            }
        }
        this.cadeiras.add(cad);
    }

    public void addDisponibilidade(Disponibilidade dispo){
        this.disponibilidades.add(dispo);
        dispo.setExplicador(this);
    }

    public boolean containsDisponibilidade(Disponibilidade disponibilidadePesquisa) {
        for(Disponibilidade disponibilidade:this.getDisponibilidades()){
            if(containsDisponibilidade(disponibilidadePesquisa,disponibilidade) ){
                return true;
            }
        }
        return false;
    }

    private boolean containsDisponibilidade(Disponibilidade disponibilidadePesquisa, Disponibilidade disponibilidade){
        return disponibilidadePesquisa.getDia().equals(disponibilidade.getDia()) && (
                disponibilidadePesquisa.getHoraInicio().isBefore(disponibilidade.getHoraInicio()) ||
                disponibilidadePesquisa.getHoraInicio().equals(disponibilidade.getHoraInicio()) ) && (
                disponibilidadePesquisa.getHoraFim().isAfter(disponibilidade.getHoraFim() ) ||
                disponibilidadePesquisa.getHoraFim().equals(disponibilidade.getHoraFim()) );
    }

    /**
     * Inclui uma nova explicação ao Explicador
     * Verifica se há Disponibilidade para o dia e hora da Explicação
     * Verifica também se não há uma Explicação prévia
     * Adiciona a Explicção no Explicador e no Aluno
     */
    public void addExplicacao(Explicacao expli, Aluno aluno){

        LocalTime hora = expli.getDia().toLocalTime();
        DayOfWeek dia = expli.getDia().getDayOfWeek();

        for(Disponibilidade disponibilidade:this.getDisponibilidades()) {
            if (dia.equals(disponibilidade.getDia()) && disponibilidade.getHoraInicio().isBefore(hora) && disponibilidade.set) {
                expli.setAluno(aluno);
                expli.setExplicador(this);
                disponibilidade.set = false;
                this.explicacoes.add(expli);
            }
        }
    }


}