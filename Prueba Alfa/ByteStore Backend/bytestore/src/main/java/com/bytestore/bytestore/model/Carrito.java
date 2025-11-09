package com.bytestore.bytestore.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Carrito")
public class Carrito {

    @Id
    @Column(name = "numCarrito", length = 10, nullable = false)
    private String numCarrito;

    @Column(name = "numCuenta", length = 10, nullable = false)
    private String numCuenta;
}
