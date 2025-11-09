package com.bytestore.bytestore.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExitoDto {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private String codigo;

    public ExitoDto(int status, String message, String path, String codigo) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.codigo = codigo;
        this.timestamp = LocalDateTime.now();
    }
    
}
