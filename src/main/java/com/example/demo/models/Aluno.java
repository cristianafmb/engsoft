package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Aluno extends BaseModel{
    private String nome;

    public Aluno(String nome) {
        this.nome=nome;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Explicacao> explicacoes = new HashSet<>();

    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Explicador explicador;


}
