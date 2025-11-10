package com.enyoi.arkana.producto.producto.service;
import com.enyoi.arkana.producto.producto.model.Producto;
import com.enyoi.arkana.producto.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<Producto> crearProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()
                || producto.getMarca() == null || producto.getMarca().isBlank()
                || producto.getCategoria() == null || producto.getCategoria().isBlank()
                || producto.getPrecio() == null || producto.getPrecio() <= 0
                || producto.getStock() == null) {
            return Mono.error(new IllegalArgumentException("Datos invalidos: ningun campo puede ser nulo o vacio y el precio debe ser mayor que 0."));
        }

        // Verificar si ya existe un producto con el mismo nombre
        return productoRepository.findByNombre(producto.getNombre())
                .flatMap(existing -> Mono.<Producto>error(new IllegalArgumentException("Ya existe un producto con el mismo nombre.")))
                .switchIfEmpty(productoRepository.save(producto));
    }

    public Flux<Producto> obtenerTodos() {

        return productoRepository.findAll();
    }


    public Flux<Producto> buscarPorMarca(String marca) {

        return productoRepository.findByMarca(marca);
    }

    public Flux<Producto> buscarPorCategoria(String categoria) {

        return productoRepository.findByCategoria(categoria);
    }

}
