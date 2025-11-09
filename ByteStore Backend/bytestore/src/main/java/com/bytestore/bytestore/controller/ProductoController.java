package com.bytestore.bytestore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bytestore.bytestore.dto.ProductoDto;
import com.bytestore.bytestore.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ProductoDto>> obtenerProductosActivos() {
        List<ProductoDto> productos = productoService.filtrarProductos(null, null, null, null);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoDto>> filtrarProductos(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) Byte formato
    ) {
        List<ProductoDto> productos = productoService.filtrarProductos(nombre, categoria, marca, formato);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/recomendados")
    public ResponseEntity<List<ProductoDto>> obtenerRecomendados(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) Byte formato
    ) {
        List<ProductoDto> filtrados = productoService.filtrarProductos(nombre, categoria, marca, formato);
        if (filtrados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProductoDto> recomendados = filtrados.stream().limit(4).collect(Collectors.toList());
        return ResponseEntity.ok(recomendados);
    }

}
