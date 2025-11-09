package com.bytestore.bytestore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @Column(name = "idProducto", length = 10)
    private String idProducto;

    @Column(name = "nombreProducto", length = 50, nullable = false)
    private String nombreProducto;

    @Column(name = "precioProducto", nullable = false)
    private double precioProducto;

    @Column(name = "stockProducto", nullable = false)
    private int stockProducto = 0;

    @Column(name = "categoriaProducto", length = 20, nullable = false)
    private String categoriaProducto;

    @Column(name = "marcaProducto", length = 25, nullable = false)
    private String marcaProducto;

    @Column(name = "formatoProducto", nullable = false)
    private byte formatoProducto; // 0 = digital, 1 = físico

    @Lob
    @Column(name = "imagenProducto", nullable = false)
    private byte[] imagenProducto;

    @Column(name = "estadoProducto", nullable = false)
    private boolean estadoProducto = true;
    
}