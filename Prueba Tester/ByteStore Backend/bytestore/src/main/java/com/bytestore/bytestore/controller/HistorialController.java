package com.bytestore.bytestore.controller;

import com.bytestore.bytestore.dto.RegistrarHistorialDto;
import com.bytestore.bytestore.dto.ExitoDto;
import com.bytestore.bytestore.model.Historial;
import com.bytestore.bytestore.service.HistorialService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<ExitoDto> registrarHistorial(
        @Valid @RequestBody RegistrarHistorialDto dto,HttpServletRequest request
    ) {
        Historial historial = historialService.registrarMovimiento(
            dto.getNumCuenta(),
            dto.getIdProducto(),
            dto.getCantProducto(),
            dto.getPrecioProducto()
        );

        ExitoDto respuesta = new ExitoDto(
            HttpStatus.CREATED.value(),
            "Movimiento registrado en historial",
            request.getRequestURI(),
            "OK_201_Historial_Registrado"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

}