package com.bookstore.service;


import com.bookstore.domain.Livro;
import com.bookstore.repositories.LivroRepository;
import com.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository repository;

    @Autowired
    public LivroService(LivroRepository repository){
        this.repository = repository;
    }
    public Livro findById(Integer id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo " + Livro.class.getName()));
    }

}
