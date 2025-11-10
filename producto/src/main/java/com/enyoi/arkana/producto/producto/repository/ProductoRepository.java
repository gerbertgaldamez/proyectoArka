package com.enyoi.arkana.producto.producto.repository;
import com.enyoi.arkana.producto.producto.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
    Mono<Producto> findByNombre(String nombre);//para validar si existe un producto con el mismo nombre
    Flux<Producto> findByMarca(String marca);
    Flux<Producto> findByCategoria(String categoria);
}
