package com.bookstore.service;

import com.bookstore.domain.Categoria;
import com.bookstore.dtos.CategoriaDTO;
import com.bookstore.repositories.CategoriaRepository;
import com.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    // Injetando o repositório de Categoria
    private final CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    // Método para buscar uma categoria por ID
    public Categoria findById(Integer id){
        // Tenta encontrar a categoria pelo ID usando Optional
        Optional<Categoria> obj = repository.findById(id);
        // Caso não encontre, lança uma exceção personalizada
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    // Método para retornar todas as categorias do banco
    public List<Categoria> findAll(){
        return repository.findAll();
    }

    // Método para criar uma nova categoria
    public Categoria create(Categoria obj){
        // Define o ID como nulo para garantir a criação de um novo registro
        obj.setId(null);
        return repository.save(obj);
    }

    // Método para atualizar uma categoria existente
    public Categoria update(Integer id, CategoriaDTO objDto) {
        // Busca a categoria pelo ID
        Categoria obj = findById(id);
        // Atualiza o nome e a descrição da categoria
        obj.setNome(objDto.getNome());
        obj.setDescricao(objDto.getDescricao());
        // Salva as mudanças no banco de dados
        return repository.save(obj);
    }

    // Método para deletar uma categoria pelo ID
    public void delete(Integer id) {
        // Verifica se a categoria existe antes de deletar
        findById(id);
        // Deleta a categoria pelo ID
        repository.deleteById(id);
    }
}
