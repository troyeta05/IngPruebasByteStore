package com.bytestore.bytestore.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductoDto {

    @NotBlank(message = "El ID del producto no puede estar vacío")
    @Size(max = 10, message = "El ID del producto debe tener máximo 10 caracteres")
    private String idProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 50, message = "El nombre del producto debe tener máximo 50 caracteres")
    private String nombreProducto;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 7, fraction = 2, message = "El precio debe tener máximo 7 dígitos enteros y 2 decimales")
    private double precioProducto;

    @NotBlank(message = "La categoría no puede estar vacía")
    @Size(max = 20, message = "La categoría debe tener máximo 20 caracteres")
    private String categoriaProducto;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(max = 25, message = "La marca debe tener máximo 25 caracteres")
    private String marcaProducto;

    @Min(value = 0, message = "El formato debe ser 0 (digital) o 1 (físico)")
    @Max(value = 1, message = "El formato debe ser 0 (digital) o 1 (físico)")
    private byte formatoProducto;

    @NotNull(message = "La imagen del producto es obligatoria")
    private byte[] imagenProducto;
    
}
