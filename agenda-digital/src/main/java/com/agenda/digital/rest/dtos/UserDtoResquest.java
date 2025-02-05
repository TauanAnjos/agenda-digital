package com.agenda.digital.rest.dtos;

import com.agenda.digital.models.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UserDtoResquest(
        String nome,
        @NotBlank(message = "Email é obrigatório.") @Email(message = "Digite um email válido.") String email,
        @NotBlank(message = "A senha é obrigatória.") String senha) {

    public UserModel toModel(){
        return new UserModel(null, this.nome, this.email,this.senha,null);
    }
}
