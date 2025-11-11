package com.enyoi.arkana.producto.producto.service;
import com.enyoi.arkana.producto.producto.model.Producto;
import com.enyoi.arkana.producto.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;



    public Mono<Producto> crearProducto(@RequestBody Producto producto) {

        System.out.println(">>> RECIBIDO EN CONTROLLER: " + producto);

        // VALIDACIÓN
        if (producto.getNombre() == null || producto.getNombre().isBlank()
                || producto.getMarca() == null || producto.getMarca().isBlank()
                || producto.getCategoria() == null || producto.getCategoria().isBlank()
                || producto.getPrecio() == null || producto.getPrecio() <= 0
                || producto.getStock() == null) {

            System.out.println("Validacion fallo — stock o algun campo viene NULL");
            return Mono.error(new IllegalArgumentException(
                    "Datos invalidos: ningun campo puede ser nulo o vacio y el precio debe ser mayor que 0."
            ));
        }

        System.out.println(">>> Validacion OK. Verificando si el nombre ya existe...");

        return productoRepository.findByNombre(producto.getNombre())
                .flatMap(existing -> {
                    return Mono.<Producto>error(
                            new IllegalArgumentException("Ya existe un producto con el mismo nombre.")
                    );
                })
                .switchIfEmpty(Mono.defer(() -> {


                    return productoRepository.save(producto)
                            .doOnSuccess(p -> System.out.println(">>> GUARDADO: " + p))
                            .doOnError(e -> {
                                System.out.println("XXX ERROR AL GUARDAR");
                                e.printStackTrace();
                            });
                }));
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
