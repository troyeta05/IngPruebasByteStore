package com.bytestore.bytestore.exception;

public class CarritoNoPerteneceALaCuentaException extends RuntimeException {
    public CarritoNoPerteneceALaCuentaException(String mensaje) {
        super(mensaje);
    }
}