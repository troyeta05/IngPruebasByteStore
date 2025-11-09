package com.bytestore.bytestore.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetCarritoId implements Serializable {
    private String numCarrito;
    private String idProducto;
}
