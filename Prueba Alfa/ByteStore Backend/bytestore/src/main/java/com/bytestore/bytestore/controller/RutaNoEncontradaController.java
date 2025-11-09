package com.bytestore.bytestore.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytestore.bytestore.dto.ErrorDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RutaNoEncontradaController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorDto> handleError(HttpServletRequest request) {
        ErrorDto error = new ErrorDto(
            HttpStatus.NOT_FOUND.value(),
            "Ruta no encontrada",
            "La ruta solicitada no existe o es inválida",
            request.getRequestURI(),
            "ERR_404_Ruta_No_Encontrada"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}

