package com.enyoi.arkana.notificacion.web;

import com.enyoi.arkana.notificacion.model.Notificacion;
import com.enyoi.arkana.notificacion.service.NotificacionService;
import com.enyoi.arkana.notificacion.web.dto.NotificacionRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/notificaciones", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping("/pedido/{pedidoId}")
    public Mono<Notificacion> notificarPedido(
            @PathVariable String pedidoId,
            @RequestParam("estado") int estado,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "telefono", required = false) String telefono,
            @RequestParam(value = "mensaje", required = false) String mensaje
    ) {
        return service.notificarPedido(pedidoId, estado, email, telefono, mensaje);
    }

    @PostMapping("/carrito/abandonado")
    public Mono<Notificacion> carritoAbandonado(@Valid @RequestBody NotificacionRequest req) {
        return service.carritoAbandonado(req.getEmail(), req.getTelefono());
    }
}
