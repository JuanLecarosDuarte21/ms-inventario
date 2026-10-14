
package com.tiendapc.inventario.repository;

import com.tiendapc.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository
        extends JpaRepository<Inventario, Long> {

    // Buscar inventario por ID del producto
    Optional<Inventario> findByProductoId(Long productoId);

    // Verificar si un producto ya tiene inventario
    boolean existsByProductoId(Long productoId);
}
