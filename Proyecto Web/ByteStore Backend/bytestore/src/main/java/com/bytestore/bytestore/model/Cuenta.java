package com.bytestore.bytestore.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cuentas")
public class Cuenta {

    @Id
    @Column(name = "numCuenta", length = 10, nullable = false)
    private String numCuenta;

    @Column(name = "correoCuenta", length = 50, nullable = false, unique = true)
    private String correoCuenta;

    @Column(name = "contrasena", length = 255, nullable = false)
    private String contrasena;

    @Column(name = "pseudonimoCuenta", length = 30, nullable = false)
    private String pseudonimoCuenta;

    @Column(name = "tipoCuenta", nullable = false)
    private Byte tipoCuenta = 0;

    @Column(name = "codigoCuenta", length = 6, nullable = false)
    private String codigoCuenta;

    @Column(name = "estadoCuenta", nullable = false)
    private Boolean estadoCuenta = true;
}
