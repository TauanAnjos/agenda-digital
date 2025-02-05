package com.agenda.digital.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authentication(String email, String password){
       try {
           UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(email, password);
           authenticationManager.authenticate(usernamePassword);

           return tokenService.generateToken(email);
       }catch (RuntimeException e){
            throw new RuntimeException("Erro ao se autenticar");
       }

    }
}
