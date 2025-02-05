package com.agenda.digital.models;

import com.agenda.digital.enums.Status;
import com.agenda.digital.rest.dtos.CompromissoDtoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_compromisso")
public class CompromissoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime data_hora;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("compromissos")
    private UserModel usuario;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaModel categoria;
    @Enumerated(EnumType.STRING)
    private Status status;

    public CompromissoModel() {
    }

    public CompromissoModel(Long id, String titulo, String descricao, LocalDateTime data_hora, UserModel usuario, CategoriaModel categoria, Status status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data_hora = data_hora;
        this.usuario = usuario;
        this.categoria = categoria;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CompromissoDtoResponse toDtoResponse(){
        return new CompromissoDtoResponse(this.titulo, this.descricao, this.data_hora, this.getUsuario().toDtoResponse(), this.categoria.toDtoResponse(), this.status);
    }
}
