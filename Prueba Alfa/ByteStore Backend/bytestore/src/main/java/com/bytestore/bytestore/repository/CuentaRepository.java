package com.bytestore.bytestore.repository;

import com.bytestore.bytestore.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    // Buscar correo de la cuenta
    Cuenta findByCorreoCuenta(String correo);

    // Buscar la existencia de una cuenta por correo
    boolean existsByCorreoCuenta(String correoCuenta);

    // Buscar por número de cuenta
    Cuenta findByNumCuenta(String numCuenta);

}