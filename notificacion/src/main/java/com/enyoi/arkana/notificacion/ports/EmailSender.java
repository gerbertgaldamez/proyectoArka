package com.enyoi.arkana.notificacion.ports;

import reactor.core.publisher.Mono;

public interface EmailSender {
    Mono<Void> send(String to, String subject, String body);
}
