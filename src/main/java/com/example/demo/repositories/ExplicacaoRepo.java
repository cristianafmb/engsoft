package com.example.demo.repositories;

import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicacao;
import com.example.demo.models.Explicador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public interface ExplicacaoRepo extends CrudRepository<Explicacao,Long> {

}
