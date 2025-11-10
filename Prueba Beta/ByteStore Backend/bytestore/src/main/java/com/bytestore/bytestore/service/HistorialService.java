package com.bytestore.bytestore.service;

import com.bytestore.bytestore.exception.CuentaNoEncontradaException;
import com.bytestore.bytestore.exception.ProductoNoEncontradoEnCarritoException;
import com.bytestore.bytestore.model.Cuenta;
import com.bytestore.bytestore.model.Producto;
import com.bytestore.bytestore.model.Historial;
import com.bytestore.bytestore.repository.HistorialRepository;
import com.bytestore.bytestore.repository.CuentaRepository;
import com.bytestore.bytestore.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class HistorialService {

    private final HistorialRepository historialRepository;
    private final CuentaRepository cuentaRepository;
    private final ProductoRepository productoRepository;

    public HistorialService(
        HistorialRepository historialRepository,
        CuentaRepository cuentaRepository,
        ProductoRepository productoRepository
    ) {
        this.historialRepository = historialRepository;
        this.cuentaRepository = cuentaRepository;
        this.productoRepository = productoRepository;
    }

    public Historial registrarMovimiento(String numCuenta, String idProducto, int cantidad, double precioUnitario) {
        Cuenta cuenta = cuentaRepository.findByNumCuenta(numCuenta);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("No se encontró la cuenta");
        }

        Producto producto = productoRepository.findByIdProducto(idProducto);
        if (producto == null) {
            throw new ProductoNoEncontradoEnCarritoException("No se encontró el producto");
        }

        Historial historial = new Historial();
        asignarNoHistorial(historial);
        historial.setCuenta(cuenta);
        historial.setProducto(producto);
        historial.setCantProducto(cantidad);
        historial.setPrecioProducto(precioUnitario);

        return historialRepository.save(historial);
    }

    // Asigar el número de historial
    public void asignarNoHistorial(Historial historial) {
        String noHistorial;
        do {
            LocalDateTime ahora = LocalDateTime.now();

            int dia = ahora.getDayOfMonth();
            int mes = ahora.getMonthValue();
            int hora = ahora.getHour();
            int minuto = ahora.getMinute();
            int noAleatorio = ThreadLocalRandom.current().nextInt(1, 100);

            String diaStr = (dia < 10) ? "0" + dia : String.valueOf(dia);
            String mesStr = (mes < 10) ? "0" + mes : String.valueOf(mes);
            String horaStr = (hora < 10) ? "0" + hora : String.valueOf(hora);
            String minutoStr = (minuto < 10) ? "0" + minuto : String.valueOf(minuto);
            String noAleatorioStr = (noAleatorio < 10) ? "0" + noAleatorio : String.valueOf(noAleatorio);

            noHistorial = diaStr + horaStr + mesStr  + minutoStr  + noAleatorioStr;
        } while (historialRepository.existsById(noHistorial));


        historial.setNumHistorial(noHistorial);
    }
}