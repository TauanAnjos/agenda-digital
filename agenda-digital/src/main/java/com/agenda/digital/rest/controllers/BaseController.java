package com.agenda.digital.rest.controllers;


import com.agenda.digital.models.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public abstract class BaseController {

    protected UserModel getUserModelSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Evita criar nova sessão
        if (session == null) {
            throw new IllegalStateException("Sessão inexistente ou expirada");
        }

        UserModel userModel = (UserModel) session.getAttribute("userModel");
        if (userModel == null) {
            throw new IllegalStateException("Usuário não encontrado na sessão");
        }
        return userModel;
    }
}
