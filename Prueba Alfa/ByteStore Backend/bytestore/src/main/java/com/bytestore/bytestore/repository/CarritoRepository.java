package com.bytestore.bytestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bytestore.bytestore.model.Carrito;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, String> {

    List<Carrito> findByNumCuenta(String numCuenta);
    
}
