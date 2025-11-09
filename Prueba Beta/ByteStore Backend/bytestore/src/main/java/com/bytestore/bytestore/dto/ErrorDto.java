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
public class ErrorDto {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private String codigo;

    public ErrorDto(int status, String error, String message, String path, String codigo) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.codigo = codigo;
        this.timestamp = LocalDateTime.now();
    }

}
