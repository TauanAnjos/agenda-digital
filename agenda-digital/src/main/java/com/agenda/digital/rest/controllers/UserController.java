package com.agenda.digital.rest.controllers;

import com.agenda.digital.models.UserModel;
import com.agenda.digital.rest.dtos.UserDtoResponse;
import com.agenda.digital.rest.dtos.UserDtoResquest;
import com.agenda.digital.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Operation(
            summary = "Registrar novo usuário.",
            description = "Endpoint para registrar um novo usuário no sistema.",
            tags = {"Usuários"}
    )
    @PostMapping
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid UserDtoResquest userDtoResquest){
        userService.cadastrarUsuario(userDtoResquest.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @Operation(
            summary = "Buscar usuário por id.",
            description = "Endpoint para buscar usuário através do id.",
            tags = {"Usuários"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> buscarUsuarioPorId(@PathVariable("id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.buscarUsuarioPorId(id));
    }
}
