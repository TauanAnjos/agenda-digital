package com.agenda.digital.rest.dtos;

import java.time.LocalDateTime;

public record UserDtoResponse(String nome,
                              String email,
                              LocalDateTime data_criacao) {
}
