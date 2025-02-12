package com.agenda.digital.rest.controllers;

import com.agenda.digital.services.NotificacaoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController extends BaseController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamNotificacoes(HttpServletRequest request) {
        Long userId = getUserModelSession(request).getId();
        return notificacaoService.getNotificacoes(userId);
    }
}
