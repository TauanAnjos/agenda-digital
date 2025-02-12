package com.agenda.digital.services;

import com.agenda.digital.models.CompromissoModel;
import com.agenda.digital.repositories.CompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificacaoService {

    @Autowired
    private CompromissoRepository compromissoRepository;

    // Criando um canal de eventos separado para cada usuário
    private final Map<Long, Sinks.Many<String>> sinks = new ConcurrentHashMap<>();

    public Flux<String> getNotificacoes(Long userId) {
        // Garante que o sink seja criado caso não exista para o usuário
        return sinks.computeIfAbsent(userId, id -> Sinks.many().multicast().onBackpressureBuffer())
                .asFlux()
                .delayElements(Duration.ofSeconds(1)); // Retarda as mensagens para simular um intervalo de envio
    }

    @Scheduled(fixedRate = 60000) // Executa a cada 60 segundos
    public void verificarCompromissos() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime daquiUmaHora = agora.plusHours(1);

        // Busca compromissos entre o horário atual e uma hora à frente
        List<CompromissoModel> proximosCompromissos = compromissoRepository
                .findByDataHoraBetween(agora, daquiUmaHora);

        for (CompromissoModel compromisso : proximosCompromissos) {
            Long userId = compromisso.getUsuario().getId();
            String mensagem = "🔔 Lembrete: " + compromisso.getTitulo() + " às " + compromisso.getData_hora();

            // Se o canal de notificações para o usuário existe, envia a mensagem
            sinks.computeIfPresent(userId, (id, sink) -> {
                System.out.println("Enviando notificação para o usuário " + userId + ": " + mensagem); // Log para depuração
                sink.tryEmitNext(mensagem); // Envia a mensagem
                return sink; // Retorna o sink sem alteração
            });
        }
    }

    // Método para garantir a inicialização do sink quando necessário
    public void garantirSink(Long userId) {
        sinks.computeIfAbsent(userId, id -> Sinks.many().multicast().onBackpressureBuffer());
    }
}
