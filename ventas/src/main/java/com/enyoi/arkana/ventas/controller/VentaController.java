package com.enyoi.arkana.ventas.controller;

import com.enyoi.arkana.ventas.model.EstadoVenta;
import com.enyoi.arkana.ventas.model.Venta;
import com.enyoi.arkana.ventas.model.VentaDetalle;
import com.enyoi.arkana.ventas.service.VentaService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public Mono<Venta> crearVenta(@RequestBody Venta venta) {
        return ventaService.crearVenta(venta);
    }

    @PostMapping("/{id}/detalle")
    public Mono<VentaDetalle> agregarDetalle(@PathVariable String id, @RequestBody VentaDetalle detalle) {
        detalle.setIdVenta(id);
        return ventaService.agregarDetalle(detalle);
    }


}
