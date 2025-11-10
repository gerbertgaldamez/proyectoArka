package com.enyoi.arkana.ventas.repository;

import com.enyoi.arkana.ventas.model.VentaDetalle;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface VentaDetalleRepository extends ReactiveMongoRepository<VentaDetalle, String> {

}
