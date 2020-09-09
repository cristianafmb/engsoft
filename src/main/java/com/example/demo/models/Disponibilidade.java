package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
public class Disponibilidade extends BaseModel{

    private DayOfWeek dia;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    public boolean set = true;
    public Disponibilidade(){}
    public Disponibilidade(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFim) {
        this.dia=dia;
        this.horaInicio=horaInicio;
        this.horaFim=horaFim;
        this.set = true;
    }
    public boolean getSet(){
        return this.set;
    }


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Explicador explicador;

}