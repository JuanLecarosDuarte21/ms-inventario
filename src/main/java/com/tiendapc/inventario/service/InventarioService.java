
package com.tiendapc.inventario.service;

import com.tiendapc.inventario.model.Inventario;
import com.tiendapc.inventario.repository.InventarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // CREATE: Registrar inventario
    public Inventario crearInventario(Inventario inventario) {

        if (inventarioRepository.existsByProductoId(
                inventario.getProductoId())) {
            throw new IllegalArgumentException(
                "Este producto ya tiene un inventario registrado");
        }

        inventario.setId(null);
        return inventarioRepository.save(inventario);
    }

    // READ: Listar todos los inventarios
    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    // READ: Buscar inventario por ID
    public Optional<Inventario> buscarPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    // READ: Buscar por ID del producto
    public Optional<Inventario> buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    // UPDATE: Actualizar inventario
    @Transactional
    public Optional<Inventario> actualizarInventario(
            Long id, Inventario datos) {

        return inventarioRepository.findById(id).map(inventario -> {

            if (!inventario.getProductoId().equals(datos.getProductoId())
                    && inventarioRepository.existsByProductoId(
                            datos.getProductoId())) {
                throw new IllegalArgumentException(
                    "El producto ya tiene un inventario registrado");
            }

            inventario.setProductoId(datos.getProductoId());
            inventario.setCantidad(datos.getCantidad());
            inventario.setStockMinimo(datos.getStockMinimo());
            inventario.setUbicacion(datos.getUbicacion());

            return inventarioRepository.save(inventario);
        });
    }

    // DELETE: Eliminar inventario
    public boolean eliminarInventario(Long id) {

        if (!inventarioRepository.existsById(id)) {
            return false;
        }

        inventarioRepository.deleteById(id);
        return true;
    }

    // CONTROL: Comprobar si hay stock bajo
    public boolean tieneStockBajo(Long id) {

        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Inventario no encontrado"));

        return inventario.getCantidad() <= inventario.getStockMinimo();
    }
}
