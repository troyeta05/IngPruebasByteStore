package com.bytestore.bytestore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrarHistorialDto {
    @NotBlank(message = "El número de cuenta no puede estar vacío")
    private String numCuenta;

    @NotBlank(message = "El ID del producto no puede estar vacío")
    private String idProducto;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantProducto;

    @Positive(message = "El precio unitario debe ser mayor a 0")
    private double precioProducto;
}
