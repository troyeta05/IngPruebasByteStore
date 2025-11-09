package com.bytestore.bytestore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bytestore.bytestore.dto.ProductoDto;
import com.bytestore.bytestore.model.Producto;
import com.bytestore.bytestore.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Filtrar productos
    public List<ProductoDto> filtrarProductos(String nombre, String categoria, String marca, Byte formato) {
        List<Producto> productos = productoRepository.filtrarProductos(nombre, categoria, marca, formato);
        return productos.stream()
                        .map(this::convertirADto)
                        .collect(Collectors.toList());
    }

    // Convertir objeto a dto
    private ProductoDto convertirADto(Producto p) {
        return new ProductoDto(
            p.getIdProducto(),
            p.getNombreProducto(),
            p.getPrecioProducto(),
            p.getCategoriaProducto(),
            p.getMarcaProducto(),
            p.getFormatoProducto(),
            p.getImagenProducto()
        );
    }
}
