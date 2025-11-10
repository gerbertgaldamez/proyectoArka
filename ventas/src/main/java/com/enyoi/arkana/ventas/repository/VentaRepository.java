package com.enyoi.arkana.ventas.repository;

import com.enyoi.arkana.ventas.model.Venta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VentaRepository extends ReactiveMongoRepository<Venta, String> {
}

