package com.bytestore.bytestore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActualizarCantidadDto {
    @NotBlank(message = "El número de carrito no puede estar vacío")
    private String numCarrito;

    @NotBlank(message = "El ID del producto no puede estar vacío")
    private String idProducto;

    @Positive(message = "La nueva cantidad debe ser mayor a cero")
    private int nuevaCantidad;
    
}