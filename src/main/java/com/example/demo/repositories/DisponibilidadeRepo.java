package com.example.demo.repositories;

import com.example.demo.models.Disponibilidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DisponibilidadeRepo extends CrudRepository<Disponibilidade, Long> {



    Iterable<Disponibilidade> findAll();

 }