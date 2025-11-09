package com.bytestore.bytestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bytestore.bytestore.model.DetCarrito;
import com.bytestore.bytestore.model.DetCarritoId;

import java.util.List;

public interface DetCarritoRepository extends JpaRepository<DetCarrito, DetCarritoId> {

    List<DetCarrito> findByNumCarrito(String numCarrito);

    boolean existsByNumCarritoAndIdProducto(String numCarrito, String idProducto);
    
}
