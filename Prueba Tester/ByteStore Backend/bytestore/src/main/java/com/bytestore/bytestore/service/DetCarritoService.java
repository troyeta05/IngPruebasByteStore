package com.bytestore.bytestore.service;

import com.bytestore.bytestore.exception.ProductoNoEncontradoEnCarritoException;
import com.bytestore.bytestore.model.DetCarrito;
import com.bytestore.bytestore.model.DetCarritoId;
import com.bytestore.bytestore.model.Producto;
import com.bytestore.bytestore.repository.DetCarritoRepository;
import com.bytestore.bytestore.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetCarritoService {

    private final DetCarritoRepository detCarritoRepository;
    private final ProductoRepository productoRepository;

    public DetCarritoService(DetCarritoRepository detCarritoRepository, ProductoRepository productoRepository) {
        this.detCarritoRepository = detCarritoRepository;
        this.productoRepository = productoRepository;
    }

    // Agregar producto al carrito
    public DetCarrito agregarProducto(DetCarrito detallePCarrito) {
        Producto producto = productoRepository.findByIdProducto(detallePCarrito.getIdProducto());
        detallePCarrito.setPrecioProducto(producto.getPrecioProducto());
        return detCarritoRepository.save(detallePCarrito);
    }

    public DetCarrito actualizarCantidad(String numCarrito, String idProducto, int nuevaCantidad) {
        DetCarritoId id = new DetCarritoId(numCarrito, idProducto);
        Optional<DetCarrito> existente = detCarritoRepository.findById(id);

        return existente.map(detalle -> {
            detalle.setCantProducto(nuevaCantidad);
            return detCarritoRepository.save(detalle);
        }).orElseThrow(() -> new ProductoNoEncontradoEnCarritoException(
            "No se encontró el producto con ID " + idProducto + " en el carrito " + numCarrito
        ));
    }

    // Eliminar producto del carrito
    public boolean eliminarProducto(String numCarrito, String idProducto) {
        DetCarritoId id = new DetCarritoId(numCarrito, idProducto);
        if (detCarritoRepository.existsById(id)) {
            detCarritoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Listar productos de un carrito
    public List<DetCarrito> listarPorCarrito(String numCarrito) {
        return detCarritoRepository.findByNumCarrito(numCarrito);
    }

}