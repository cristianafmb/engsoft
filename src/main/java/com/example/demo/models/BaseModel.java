package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    public Long getId(){
        return this.id;
    }

}



