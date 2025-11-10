package com.enyoi.arkana.producto.producto.controller;
import com.enyoi.arkana.producto.producto.model.Producto;
import com.enyoi.arkana.producto.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public Mono<Producto> crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @GetMapping
    public Flux<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/marca/{marca}")
    public Flux<Producto> buscarPorMarca(@PathVariable String marca) {
        return productoService.buscarPorMarca(marca);
    }

    @GetMapping("/categoria/{categoria}")
    public Flux<Producto> buscarPorCategoria(@PathVariable String categoria) {
        return productoService.buscarPorCategoria(categoria);
    }

}
