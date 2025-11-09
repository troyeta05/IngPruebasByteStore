package com.bytestore.bytestore.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Historial")
public class Historial {

    @Id
    @Column(name = "numHistorial", length = 10, nullable = false)
    private String numHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numCuenta", nullable = false)
    private Cuenta cuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @Column(name = "cantProducto", nullable = false)
    private int cantProducto = 1;

    @Column(name = "precioProducto", nullable = false)
    private double precioProducto;

}
