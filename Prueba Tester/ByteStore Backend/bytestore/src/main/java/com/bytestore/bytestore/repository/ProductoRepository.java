package com.bytestore.bytestore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bytestore.bytestore.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    @Query("SELECT p FROM Producto p WHERE p.estadoProducto = true "
     + "AND (:nombre IS NULL OR LOWER(p.nombreProducto) LIKE LOWER(CONCAT('%', :nombre, '%'))) "
     + "AND (:categoria IS NULL OR p.categoriaProducto = :categoria) "
     + "AND (:marca IS NULL OR p.marcaProducto = :marca) "
     + "AND (:formato IS NULL OR p.formatoProducto = :formato)")
    List<Producto> filtrarProductos(
        @Param("nombre") String nombre,
        @Param("categoria") String categoria,
        @Param("marca") String marca,
        @Param("formato") Byte formato
    );

    Producto findByIdProducto(String idProducto);
    
}
