package com.agenda.digital.rest.dtos;

import com.agenda.digital.enums.Status;

public record TarefaDtoRquest(String descricao,
                              Long compromis_id,
                              Status status) {
}
