package com.bytestore.bytestore.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Configuration
public class ZonaHorariaConfig {

    @PostConstruct
    public void establecerZonaHoraria() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"));
        System.out.println("Zona horaria configurada: America/Mexico_City");
        System.out.println(LocalDateTime.now());
    }

}