package com.enyoi.arkana.notificacion.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificacionRequest {

    @NotBlank
    private String pedidoId;

    @NotNull
    private Integer estado; // 1,2,4,3

    private String email;
    private String telefono;

    private String mensaje;

    public String getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
