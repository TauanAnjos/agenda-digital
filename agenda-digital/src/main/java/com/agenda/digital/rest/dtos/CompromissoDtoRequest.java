package com.agenda.digital.rest.dtos;

import com.agenda.digital.enums.Status;
import com.agenda.digital.models.CategoriaModel;
import com.agenda.digital.models.CompromissoModel;
import com.agenda.digital.models.UserModel;

import java.time.LocalDateTime;

public record CompromissoDtoRequest(String titulo,
                                    String descricao,
                                    LocalDateTime data_hora,
                                    Long categoriaId,

                                    Status status) {

}
