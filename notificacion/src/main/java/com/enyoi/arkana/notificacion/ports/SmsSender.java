package com.enyoi.arkana.notificacion.ports;

import reactor.core.publisher.Mono;

public interface SmsSender {
    Mono<Void> send(String to, String body);
}
