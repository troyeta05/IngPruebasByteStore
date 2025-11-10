package com.bytestore.bytestore.service;

import com.bytestore.bytestore.model.Carrito;
import com.bytestore.bytestore.repository.CarritoRepository;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    // Crear el carrito al usuario
    public Carrito crearCarrito(Carrito carrito) {
        asignarNoCarrito(carrito);
        return carritoRepository.save(carrito);
    }

    // Asignar el número de carrito
    public void asignarNoCarrito(Carrito carrito) {
        String noCarrito;
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

            noCarrito = noAleatorioStr + diaStr + minutoStr + mesStr + horaStr;
        } while (carritoRepository.existsById(noCarrito));

        carrito.setNumCarrito(noCarrito);
    }

}