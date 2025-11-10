package com.bytestore.bytestore.controller;

import com.bytestore.bytestore.dto.AgregarProductoDto;
import com.bytestore.bytestore.dto.ActualizarCantidadDto;
import com.bytestore.bytestore.dto.EliminarProductoDto;
import com.bytestore.bytestore.dto.ErrorDto;
import com.bytestore.bytestore.dto.ExitoDto;
import com.bytestore.bytestore.exception.CarritoNoEncontradoException;
import com.bytestore.bytestore.exception.CarritoNoPerteneceALaCuentaException;
import com.bytestore.bytestore.model.Carrito;
import com.bytestore.bytestore.model.DetCarrito;
import com.bytestore.bytestore.repository.CarritoRepository;
import com.bytestore.bytestore.service.DetCarritoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class DetCarritoController {

    private final DetCarritoService detCarritoService;
    private final CarritoRepository carritoRepository;

    public DetCarritoController(DetCarritoService detCarritoService, CarritoRepository carritoRepository) {
        this.detCarritoService = detCarritoService;
        this.carritoRepository = carritoRepository;
    }


    @PostMapping("/agregar")
    public ResponseEntity<DetCarrito> agregarProducto(@Valid @RequestBody AgregarProductoDto agregarProductoDto) {
        DetCarrito detalle = new DetCarrito();
        detalle.setNumCarrito(agregarProductoDto.getNumCarrito());
        detalle.setIdProducto(agregarProductoDto.getIdProducto());
        detalle.setCantProducto(agregarProductoDto.getCantProducto());

        DetCarrito creado = detCarritoService.agregarProducto(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DetCarrito> actualizarCantidad(@Valid @RequestBody ActualizarCantidadDto dto) {
        DetCarrito actualizado = detCarritoService.actualizarCantidad(
            dto.getNumCarrito(),
            dto.getIdProducto(),
            dto.getNuevaCantidad()
        );
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarProducto(
        @Valid @RequestBody EliminarProductoDto dto,
        HttpServletRequest request
    ) {
        boolean eliminado = detCarritoService.eliminarProducto(dto.getNumCarrito(), dto.getIdProducto());

        if (eliminado) {
            ExitoDto respuesta = new ExitoDto(
                200,
                "Producto eliminado del carrito",
                request.getRequestURI(),
                "OK_200_Producto_Eliminado"
            );
            return ResponseEntity.ok(respuesta);
        } else {
            ErrorDto respuesta = new ErrorDto(
                404,
                "Producto no encontrado en el carrito",
                "No se pudo eliminar el producto porque no existe en el carrito",
                request.getRequestURI(),
                "ERR_404_Producto_No_Encontrado"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @GetMapping("/carrito")
    public List<DetCarrito> listarPorCarritoYCuenta(@RequestParam String numCarrito, @RequestParam String numCuenta) {
        Carrito carrito = carritoRepository.findById(numCarrito).orElseThrow(() ->
            new CarritoNoEncontradoException("No se encontró el carrito"));

        if (!carrito.getNumCuenta().equals(numCuenta)) {
            throw new CarritoNoPerteneceALaCuentaException("El carrito no pertenece a la cuenta");
        }

        return detCarritoService.listarPorCarrito(numCarrito);
    }

}
