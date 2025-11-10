package com.enyoi.arkana.stock.inventario.repository;

import com.enyoi.arkana.stock.inventario.model.Inventario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface InventarioRepository extends ReactiveMongoRepository<Inventario, String> {
    Mono<Inventario> findByIdProducto(String idProducto);
}
