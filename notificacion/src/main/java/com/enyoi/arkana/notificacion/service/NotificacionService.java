package com.enyoi.arkana.notificacion.service;

import com.enyoi.arkana.notificacion.model.Notificacion;
import com.enyoi.arkana.notificacion.model.EstadoEnvio;
import com.enyoi.arkana.notificacion.model.Evento;
import com.enyoi.arkana.notificacion.ports.EmailSender;
import com.enyoi.arkana.notificacion.ports.SmsSender;
import com.enyoi.arkana.notificacion.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

@Service
public class NotificacionService {

    private final NotificacionRepository repo;
    private final EmailSender emailSender;
    private final SmsSender smsSender;

    public NotificacionService(NotificacionRepository repo, EmailSender emailSender, SmsSender smsSender) {
        this.repo = repo;
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    public Mono<Notificacion> notificarPedido(String pedidoId, int estado, String email, String telefono, String overrideMensaje) {
        final Evento evento = Evento.fromEstadoCode(estado);
        final String asunto = switch (evento) {
            case CONFIRMAR -> "Confirmacion de pedido " + pedidoId;
            case DESPACHAR -> "Tu pedido " + pedidoId + " fue despachado";
            case ENTREGADO -> "Pedido " + pedidoId + " entregado";
            case CANCELADO -> "Pedido " + pedidoId + " cancelado";
            case CARRITO_ABANDONADO -> "Aun hay productos en tu carrito";
        };

        final String mensaje = overrideMensaje != null && !overrideMensaje.isBlank()
                ? overrideMensaje
                : plantillaMensaje(evento, pedidoId);

        Mono<Void> envioMono;
        String canal, destinatario;

        if (email != null && !email.isBlank()) {
            canal = "EMAIL";
            destinatario = email;
            envioMono = emailSender.send(email, asunto, mensaje);
        } else if (telefono != null && !telefono.isBlank()) {
            canal = "SMS";
            destinatario = telefono;
            envioMono = smsSender.send(telefono, mensaje);
        } else {
            return Mono.error(new IllegalArgumentException("Se requiere email o telefono."));
        }

        Notificacion n = new Notificacion();
        n.setPedidoId(pedidoId);
        n.setEvento(evento.name());
        n.setCanal(canal);
        n.setDestinatario(destinatario);
        n.setAsunto(asunto);
        n.setMensaje(mensaje);
        n.setEstadoEnvio(EstadoEnvio.PENDIENTE.name());
        n.setCreadoEn(OffsetDateTime.now());

        return repo.save(n)
                .flatMap(saved -> envioMono
                        .then(Mono.defer(() -> {
                            saved.setEstadoEnvio(EstadoEnvio.ENVIADO.name());
                            saved.setEnviadoEn(OffsetDateTime.now());
                            return repo.save(saved);
                        }))
                        .onErrorResume(ex -> {
                            saved.setEstadoEnvio(EstadoEnvio.FALLIDO.name());
                            saved.setError(ex.getMessage());
                            return repo.save(saved);
                        }));
    }

    public Mono<Notificacion> carritoAbandonado(String email, String telefono) {
        String pedidoId = "carrito-" + System.currentTimeMillis();
        return notificarPedido(pedidoId, 0, email, telefono, "¡Aun tienes productos en tu carrito!");
    }

    private String plantillaMensaje(Evento evento, String pedidoId) {
        return switch (evento) {
            case CONFIRMAR -> "Tu pedido " + pedidoId + " ha sido confirmado.";
            case DESPACHAR -> "Tu pedido " + pedidoId + " fue despachado. ¡Va en camino!";
            case ENTREGADO -> "¡Listo! Pedido " + pedidoId + " fue entregado.";
            case CANCELADO -> "El pedido " + pedidoId + " fue cancelado.";
            case CARRITO_ABANDONADO -> "Aun tienes productos en tu carrito.";
        };
    }
}
