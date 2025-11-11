package com.enyoi.arkana.notificacion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.OffsetDateTime;

@Table("notificacion")
public class Notificacion {

    @Id
    private Long id;

    @Column("pedido_id")
    private String pedidoId;

    private String canal;       // EMAIL | SMS
    private String evento;      // CONFIRMAR | DESPACHAR | ENTREGADO | CANCELADO | CARRITO_ABANDONADO

    private String destinatario;
    private String asunto;
    private String mensaje;

    @Column("estado_envio")
    private String estadoEnvio; // PENDIENTE | ENVIADO | FALLIDO

    private String error;

    @Column("enviado_en")
    private OffsetDateTime enviadoEn;

    @Column("creado_en")
    private OffsetDateTime creadoEn;

    // getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getCanal() {
        return canal;
    }
    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }
    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    public OffsetDateTime getEnviadoEn() {
        return enviadoEn;
    }
    public void setEnviadoEn(OffsetDateTime enviadoEn) {
        this.enviadoEn = enviadoEn;
    }

    public OffsetDateTime getCreadoEn() {
        return creadoEn;
    }
    public void setCreadoEn(OffsetDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}
