package com.bytestore.bytestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bytestore.bytestore.dto.ErrorDto;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Excepción de cuenta no encontrada
    @ExceptionHandler(CuentaNoEncontradaException.class)
    public ResponseEntity<ErrorDto> handleCuentaNoEncontrada(CuentaNoEncontradaException ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto(
            HttpStatus.NOT_FOUND.value(),
            "Cuenta no encontrada",
            ex.getMessage(),
            request.getRequestURI(),
            "ERR_404_Correo_No_Encontrado"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Excepción de código de recuperación inválido
    @ExceptionHandler(CodigoRecuperacionInvalidoException.class)
    public ResponseEntity<ErrorDto> handleCodigoRecuperacionInvalidoException(CodigoRecuperacionInvalidoException ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto(
            HttpStatus.CONFLICT.value(),
            "Código de recuperación inválido",
            ex.getMessage(),
            request.getRequestURI(),
            "ERR_409_Código_Recuperación_Invalido"
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Excepción de correo duplicado
    @ExceptionHandler(CorreoDuplicadoException.class)
    public ResponseEntity<ErrorDto> handleCorreoDuplicadoException(CorreoDuplicadoException ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto(
            HttpStatus.CONFLICT.value(),
            "Correo duplicado",
            ex.getMessage(),
            request.getRequestURI(),
            "ERR_409_Correo_Duplicado"
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Excepciones de servidor
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleUnexpected(Exception ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error interno inesperado",
            ex.getMessage(),
            request.getRequestURI(),
            "ERR_500_Error_Servidor"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
