package com.bookstore.dtos;

import com.bookstore.domain.Livro;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

public class LivroDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo TÍTULO é obrigatório")
    @Length(min = 3, max = 50, message = "O campo TÍTULO deve ter entre 3 e 50 caracteres")
    private String titulo;
    @JsonProperty("nome_autor")
    @NotEmpty(message = "O campo NOME DO AUTOR é obrigatório")
    @Length(min = 3, max = 50, message = "O campo NOME DO AUTOR deve ter entre 3 e 50 caracteres")
    private String nomeAutor;

    public LivroDTO(){
        super();
    }

    public LivroDTO(Livro obj){
        super();
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.nomeAutor = obj.getNomeAutor();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
}
