package com.bookstore.service;


import com.bookstore.domain.Categoria;
import com.bookstore.domain.Livro;
import com.bookstore.repositories.LivroRepository;
import com.bookstore.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final CategoriaService categoriaService;
    @Autowired
    public LivroService(LivroRepository repository, CategoriaService categoriaService){
        this.repository = repository;
        this.categoriaService = categoriaService;
    }
    public Livro findById(Integer id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo " + Livro.class.getName()));
    }


    public List<Livro> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        return repository.findAllByCategoria(id_cat);

    }

    public Livro update(Integer id, Livro partialLivro) {
        Livro existingLivro = findById(id);
        updateData(existingLivro, partialLivro);
        return repository.save(existingLivro);
    }

    private void updateData(Livro existingLivro, Livro partialLivro) {
        if (partialLivro.getTitulo() != null) {
            existingLivro.setTitulo(partialLivro.getTitulo());
        }
        if (partialLivro.getNomeAutor() != null) {
            existingLivro.setNomeAutor(partialLivro.getNomeAutor());
        }
        if (partialLivro.getTexto() != null) {
            existingLivro.setTexto(partialLivro.getTexto());
        }
        // Adicionar mais verificações caso necessário
    }

    public Livro create(Integer id_cat, Livro obj){
        obj.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        obj.setCategoria(cat);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        Livro obj = findById(id);
        repository.delete(obj);

    }
}
