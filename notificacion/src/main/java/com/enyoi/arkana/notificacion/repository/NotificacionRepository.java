package com.enyoi.arkana.notificacion.repository;

import com.enyoi.arkana.notificacion.model.Notificacion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface NotificacionRepository extends ReactiveCrudRepository<Notificacion, Long> {
    Flux<Notificacion> findByPedidoId(String pedidoId);
}
