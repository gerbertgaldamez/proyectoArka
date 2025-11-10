package com.enyoi.arkana.stock.inventario.service;

import com.enyoi.arkana.stock.inventario.model.Inventario;
import com.enyoi.arkana.stock.inventario.model.ProductoResponse;
import com.enyoi.arkana.stock.inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class InventarioService {

    private final InventarioRepository repository;
    private final WebClient webClient;

    public InventarioService(InventarioRepository repository,
                             @Value("${producto.service.url}") String productosUrl) {
        this.repository = repository;
        this.webClient = WebClient.builder()
                .baseUrl(productosUrl)
                .build();
    }

    public Mono<Inventario> aumentarStock(String idProducto, int cantidad) {
        return repository.findByIdProducto(idProducto)
                .flatMap(inv -> {
                    inv.setCantidad(inv.getCantidad() + cantidad);
                    return repository.save(inv);
                })
                .switchIfEmpty(repository.save(new Inventario(idProducto, cantidad)));
    }

    public Mono<Inventario> disminuirStock(String idProducto, int cantidad) {
        return repository.findByIdProducto(idProducto)
                .flatMap(inv -> {
                    if (inv.getCantidad() - cantidad < 0) {
                        return Mono.error(new RuntimeException("No se puede disminuir stock por debajo de 0"));
                    }
                    inv.setCantidad(inv.getCantidad() - cantidad);
                    return repository.save(inv);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("El producto no existe en inventario")));
    }

    public Flux<Inventario> listarInventario() {
        return repository.findAll();
    }

    public Object obtenerInventarioDetallado(String idProducto) {
        return repository.findByIdProducto(idProducto)
                .flatMap(inv ->
                        webClient.get()
                                .uri("/api/productos/" + idProducto)
                                .retrieve()
                                .bodyToMono(ProductoResponse.class)
                                .map(prod -> new Object() {
                                    public final String id = inv.getId();
                                    public final String idProducto = inv.getIdProducto();
                                    public final Integer cantidad = inv.getCantidad();
                                    public final String nombre = prod.getNombre();
                                    public final String marca = prod.getMarca();
                                    public final String categoria = prod.getCategoria();
                                    public final Double precio = prod.getPrecio();
                                })
                )
                .switchIfEmpty(Mono.error(new RuntimeException("El producto no existe en inventario")));
    }
}
