package com.agenda.digital.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notificacao")
public class NotificacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "compromisso_id")
    private CompromissoModel compromisso_id;
    private LocalDateTime data_hora_envio;
    private String mensagem;

    public NotificacaoModel() {
    }

    public NotificacaoModel(Long id, CompromissoModel compromisso_id, LocalDateTime data_hora_envio, String mensagem) {
        this.id = id;
        this.compromisso_id = compromisso_id;
        this.data_hora_envio = data_hora_envio;
        this.mensagem = mensagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompromissoModel getCompromisso_id() {
        return compromisso_id;
    }

    public void setCompromisso_id(CompromissoModel compromisso_id) {
        this.compromisso_id = compromisso_id;
    }

    public LocalDateTime getData_hora_envio() {
        return data_hora_envio;
    }

    public void setData_hora_envio(LocalDateTime data_hora_envio) {
        this.data_hora_envio = data_hora_envio;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
