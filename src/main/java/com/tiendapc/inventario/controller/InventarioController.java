
package com.tiendapc.inventario.controller;

import com.tiendapc.inventario.model.Inventario;
import com.tiendapc.inventario.service.InventarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // POST: Crear inventario
    @PostMapping
    public ResponseEntity<?> crearInventario(
            @Valid @RequestBody Inventario inventario) {

        try {
            Inventario nuevo = inventarioService.crearInventario(inventario);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevo);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // GET: Listar inventarios
    @GetMapping
    public List<Inventario> listarInventarios() {
        return inventarioService.listarInventarios();
    }

    // GET: Buscar inventario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {

        return inventarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Buscar inventario por ID del producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> buscarPorProductoId(
            @PathVariable Long productoId) {

        return inventarioService.buscarPorProductoId(productoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT: Actualizar inventario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInventario(
            @PathVariable Long id,
            @Valid @RequestBody Inventario datos) {

        try {
            return inventarioService.actualizarInventario(id, datos)
                    .map(inventario -> ResponseEntity.ok((Object) inventario))
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // DELETE: Eliminar inventario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {

        if (inventarioService.eliminarInventario(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    // GET: Consultar si el stock esta bajo
    @GetMapping("/{id}/stock-bajo")
    public ResponseEntity<Boolean> consultarStockBajo(@PathVariable Long id) {

        if (inventarioService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventarioService.tieneStockBajo(id));
    }
}
