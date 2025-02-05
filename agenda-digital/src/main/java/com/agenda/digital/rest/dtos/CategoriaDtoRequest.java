package com.agenda.digital.rest.dtos;

import com.agenda.digital.models.CategoriaModel;

public record CategoriaDtoRequest(Long id,
                                  String nome) {
    public CategoriaModel toModel(){
        return new CategoriaModel(this.id,this.nome);
    }
}
