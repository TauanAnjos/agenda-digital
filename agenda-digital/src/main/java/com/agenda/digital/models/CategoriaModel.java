package com.agenda.digital.models;

import com.agenda.digital.rest.dtos.CategoriaDtoResponse;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_categoria")
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public CategoriaModel() {
    }

    public CategoriaModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaDtoResponse toDtoResponse(){
        return new CategoriaDtoResponse(this.nome);
    }
}
