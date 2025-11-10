package com.enyoi.arkana.ventas.service;

import com.enyoi.arkana.ventas.model.EstadoVenta;
import com.enyoi.arkana.ventas.model.Venta;
import com.enyoi.arkana.ventas.model.VentaDetalle;
import com.enyoi.arkana.ventas.repository.VentaDetalleRepository;
import com.enyoi.arkana.ventas.repository.VentaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final VentaDetalleRepository detalleRepository;

    public VentaService(VentaRepository ventaRepository, VentaDetalleRepository detalleRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleRepository = detalleRepository;
    }

    public Mono<Venta> crearVenta(Venta venta) {
        venta.setEstadoVenta(EstadoVenta.EN_PROCESO);
        venta.setFechaCreacion(java.time.LocalDate.now().toString());
        return ventaRepository.save(venta);
    }

    //
    public Mono<VentaDetalle> agregarDetalle(VentaDetalle detalle) {

        return detalleRepository.save(detalle);
    }
}

