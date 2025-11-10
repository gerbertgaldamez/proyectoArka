package com.enyoi.arkana.stock.inventario.controller;

import com.enyoi.arkana.stock.inventario.model.Inventario;
import com.enyoi.arkana.stock.inventario.service.InventarioService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/inventario")
public class ProductoController {

    private InventarioService service;

    public void InventarioController(InventarioService service) {
        this.service = service;
    }

    @PostMapping("/aumentar/{idProducto}/{cantidad}")
    public Mono<Inventario> aumentar(@PathVariable String idProducto, @PathVariable int cantidad) {
        return service.aumentarStock(idProducto, cantidad);
    }

    @PostMapping("/disminuir/{idProducto}/{cantidad}")
    public Mono<Inventario> disminuir(@PathVariable String idProducto, @PathVariable int cantidad) {
        return service.disminuirStock(idProducto, cantidad);
    }

    @GetMapping
    public Flux<Inventario> listar() {
        return service.listarInventario();
    }

    @GetMapping("/{idProducto}")
    public Mono<Object> obtenerPorIdProducto(@PathVariable String idProducto) {
        return (Mono<Object>) service.obtenerInventarioDetallado(idProducto);
    }
}
