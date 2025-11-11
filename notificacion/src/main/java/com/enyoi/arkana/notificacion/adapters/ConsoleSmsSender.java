package com.enyoi.arkana.notificacion.adapters;

import com.enyoi.arkana.notificacion.ports.SmsSender;
import reactor.core.publisher.Mono;

public class ConsoleSmsSender implements SmsSender {
    @Override
    public Mono<Void> send(String to, String body) {
        System.out.println("[SMS] to=" + to + " body=" + body);
        return Mono.empty();
    }
}
