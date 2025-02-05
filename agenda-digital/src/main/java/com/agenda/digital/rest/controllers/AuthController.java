package com.agenda.digital.rest.controllers;

import com.agenda.digital.rest.dtos.AuthDto;
import com.agenda.digital.rest.dtos.TokenDto;
import com.agenda.digital.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{
    @Autowired
    private AuthService  authService;


    @Operation(
            summary = "Autenticar usuário",
            description = "Endpoint para autenticar um usuário no sistema. Requer o envio de e-mail e senha válidos, retornando um token JWT em caso de sucesso.",
            tags = {"Autenticação"}
    )
    @PostMapping("/login")
    public TokenDto login(@RequestBody AuthDto authDto){
        return new TokenDto(authService.authentication(authDto.email(), authDto.senha()));
    }
}
