package com.bookstore.resources;

import com.bookstore.domain.Livro;
import com.bookstore.dtos.LivroDTO;
import com.bookstore.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    private final LivroService service;

    @Autowired
    public LivroResource(LivroService service) {
        this.service = service;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){
        Livro obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat){
        List<Livro> list = service.findAll(id_cat);
        List<LivroDTO> listDTO = list.stream().map(LivroDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @Valid @RequestBody Livro obj){
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @Valid @RequestBody Livro obj){
        Livro newObj = service.updatePartial(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
                                        @Valid @RequestBody Livro obj) {
        Livro newObj = service.create(id_cat, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);  // Retornar o novo objeto criado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
