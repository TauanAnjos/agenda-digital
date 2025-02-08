package com.agenda.digital.rest.dtos;

import com.agenda.digital.enums.Status;

public record TarefaDtoResponse(String descricao,
                                Status status) {
}
