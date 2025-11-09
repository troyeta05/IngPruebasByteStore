package com.bytestore.bytestore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bytestore.bytestore.dto.AccesoDto;
import com.bytestore.bytestore.dto.CambioContrasenaDto;
import com.bytestore.bytestore.dto.CrearCuentaDto;
import com.bytestore.bytestore.dto.EnvioCodigoDto;
import com.bytestore.bytestore.dto.ExitoDto;
import com.bytestore.bytestore.model.Cuenta;
import com.bytestore.bytestore.service.CuentaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }
    
    @PostMapping("/crear")
    public ResponseEntity<ExitoDto> crearCuenta(@Valid @RequestBody CrearCuentaDto crearCuentaDto,HttpServletRequest request) {
        Cuenta cuenta = new Cuenta();
        cuenta.setCorreoCuenta(crearCuentaDto.getCorreoCuenta());
        cuenta.setContrasena(crearCuentaDto.getContrasena());
        String contrasena = crearCuentaDto.getContrasena();
        cuenta.setPseudonimoCuenta(crearCuentaDto.getPseudonimoCuenta());
        cuenta.setTipoCuenta((byte) 0);
        cuenta.setEstadoCuenta(true);

        cuentaService.registrarCuenta(cuenta);
        cuentaService.enviarCredencialesPorCorreo(cuenta,contrasena);

        ExitoDto respuesta = new ExitoDto(
            201,
            "Cuenta creada exitosamente",
            request.getRequestURI(),
            "OK_201_Cuenta_Creada"
        );

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/enviar")
    public ResponseEntity<ExitoDto> enviarCodigo(@Valid @RequestBody EnvioCodigoDto envioCodigoDto, HttpServletRequest request) {
        cuentaService.enviarCodigoRecuperacion(envioCodigoDto.getCorreoCuenta());

        ExitoDto respuesta = new ExitoDto(
            200,
            "Código de recuperación enviado exitosamente",
            request.getRequestURI(),
            "OK_200_Codigo_Enviado"
        );

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/cambiar")
    ResponseEntity<ExitoDto> cambiarContrasena(@Valid @RequestBody CambioContrasenaDto cambioContrasenaDto, HttpServletRequest request) {
        cuentaService.cambiarContrasena(cambioContrasenaDto.getCorreoCuenta(), cambioContrasenaDto.getCodigoCuenta(), cambioContrasenaDto.getContrasena());

        ExitoDto respuesta = new ExitoDto(
            200,
            "Contraseña actualizada exitosamente",
            request.getRequestURI(),
            "OK_200_Codigo_Enviado"
        );

        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/login")
    public ResponseEntity<ExitoDto> login(@Valid @RequestBody AccesoDto accesoDto, HttpServletRequest request) {
        cuentaService.validarCredenciales(accesoDto.getCorreoCuenta(), accesoDto.getContrasena());

        ExitoDto respuesta = new ExitoDto(
            200,
            "Acceso concedido",
            request.getRequestURI(),
            "OK_200_Codigo_Enviado"
        );

        return ResponseEntity.ok(respuesta);
    }

}