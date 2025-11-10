package com.bytestore.bytestore.model;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DetCarrito")
@IdClass(DetCarritoId.class)
public class DetCarrito {

    @Id
    @Column(name = "numCarrito", length = 10, nullable = false)
    private String numCarrito;

    @Id
    @Column(name = "idProducto", length = 10, nullable = false)
    private String idProducto;

    @Column(name = "cantProducto", nullable = false)
    private int cantProducto = 0;

    @Column(name = "precioProducto", nullable = false)
    private double precioProducto;
}
